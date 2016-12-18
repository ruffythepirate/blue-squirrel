

Vue.component('markdown-editor', {
  template: '<div class="form-group">'
            + '<textarea :value="text" @input="update" class="form-control" id="body" name="body"> </textarea>'
            + '<div v-html="compiledMarkdown"></div>'
            + '</div>',
  props: ['value'],
  data: function() {
    return {
      text: ''
    }
  },
  computed: {
    compiledMarkdown: function() {
      return marked(this.text, {sanitize: true})
    }
  },
  methods: {
    update: _.debounce(function(e) {
      this.$data.text = e.target.value
      this.$emit('input', this.value)
    }, 500)
  }
});

new Vue({
    el: '#editor',

    data: {
        entry: {title: '', body: ''}
    },
    methods: {
        save: function() {
            this.$http.post('/api/posts', this.entry).then(function(){
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