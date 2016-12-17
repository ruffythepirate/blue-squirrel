
new Vue({
    el: '#editor',

    data: {
        entry: {title: '', body: ''}
    },
    methods: {
        save: function() {
            this.$http.post('/api/posts', { testData: {property: 'Hello!'}}).then(function(){
                console.log('success!');
            }, function(){
               console.err('request failed!');
            })
        },
        cancel: function() {
            console.log('cancel!')
        }
    }
})