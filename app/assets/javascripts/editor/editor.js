
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