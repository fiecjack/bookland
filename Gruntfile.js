module.exports = function(grunt) {

    require('load-grunt-tasks')(grunt);

    var srcFolder = 'src/main/javascript';
    var targetFolder = grunt.option('target');
    var destSrcFolder = targetFolder + '/app/js';
    var bowerLib = targetFolder + '/app/lib';


    grunt.initConfig({

        pkg: grunt.file.readJSON('package.json'),

        "bower-install-simple": {
            options: {
                color: true,
                directory: bowerLib
            },
            "prod": {
                options: {
                    production: true
                }
            },
            "dev": {
                options: {
                    production: false
                }
            }
        },

        jshint: {
            all: ['Gruntfile.js', bowerLib+'/**/ *.js', srcFolder+'/**/*.js']
        },

        concat: {
            options: {
                separator: ';',
                banner: "'use strict';\n",
                process: function(src, filepath) {
                    var res = '// Source: ' + filepath + '\n' +
                        src.replace(/(^|\n)[ \t]*('use strict'|"use strict");?\s*/g, '$1');

                    return res.replace(/\$\{baseurl\}/g, grunt.option('baseurl'));
                }
            },
            dist: {
                src: [srcFolder+'/**/*.js'],
                dest: destSrcFolder+ '/<%= pkg.name %>.js'
            }
        },

        uglify: {
            build: {
                src: destSrcFolder+ '/<%= pkg.name %>.js',
                dest: destSrcFolder+ '/<%= pkg.name %>.min.js'
            }
        },

        wiredep: {

            task: {
                src: [
                    targetFolder +'/app/index.html'
                ],
                options: {
                    directory : bowerLib
                }
            }
        },

        copy: {
            main: {
                files: [
                    {expand: true, src: [srcFolder+'/**/*.js'], dest: destSrcFolder, filter: 'isFile', flatten: true}
                ]
            }
        },

        watch: {
            files: [srcFolder+'/**/*.js'],
            tasks: ['concat']
        }

    });

    grunt.registerTask('dev', ['bower-install-simple','concat','wiredep']);
    grunt.registerTask('default', ['bower-install-simple','jshint','concat','uglify','wiredep']);

};