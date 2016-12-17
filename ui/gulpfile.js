var gulp = require('gulp')

var source = require('vinyl-source-stream')
var del = require('del')
var karma = require('Karma')
var targetFolder = '../public/'

var testTarget = 'build-test/'


gulp.task('build-test', function() {
        gulp.src('../app/assets/javascripts/**/*.js')
        .pipe(gulp.dest('./' + testTarget));
})

gulp.task('test', ['build-test'], function(done) {
    new karma.Server({
        configFile: __dirname + '/karma.conf.js',
        singleRun: true
    }, done).start();
});

//gulp.task('clean', function() {
//    del([targetFolder + '*', '!' + targetFolder + 'WEB-INF'], { force: true });
//});

//gulp.task('watch', function() {
//    gulp.watch(['app/**/*.js', 'app/**/*.html', 'styles/**/*.css'], ['compile'])
//})

//gulp.task('default', ['compile', 'watch'])
