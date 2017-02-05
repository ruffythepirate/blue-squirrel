

Vue.component('markdown-editor', {
	template: '<div class="markdown-editor">'
	+ '<textarea :value="text" tabindex="2" @input="update" id="body" name="body"> </textarea>'
	+ '<div v-html="compiledMarkdown"></div>'
	+ '</div>',
	props: ['value'],
	data: function() {
		return {
			text: '',
			compiledMarkdown: ''
		}
},
	methods: {
		update: _.debounce(function(e) {

			this.$data.text = e.target.value;
			this.$emit('input', this.value);

			updateMarkdown(this, e.target.value);

		}, 700)
	}
});

function updateMarkdown(self, newText) {

	if(newText === self.lastSentText) return;
	self.lastSentText = newText;
	var urlEncodedText = encodeURIComponent(newText);

	fetch('/api/post/parseMarkdown?markdown=' + urlEncodedText)
		.then(function(response) {
			return response.text();
		}). then(function(data) {
			self.$data.compiledMarkdown = data;
		});
}

new Vue({
	el: '#editor',

	data: {
		entry: {title: '', body: ''}
	},
	methods: {
		save: function() {

			const tags = this.entry.tags
				.split(",")
				.map(tag => tag.trim())
				.reduce((total,tag) => {if(total.indexOf(tag) < 0) total.push(tag); return total;}, [])

			const entryToSend = Vue.util.extend({}, this.entry)
			entryToSend.tags = tags
			this.$http.post('/api/posts', entryToSend).then(function(){
				console.log('success!');
			}, function(){
				console.error('request failed!');
			})
		},
		cancel: function() {
			console.log('cancel!')
		}
	}
})
