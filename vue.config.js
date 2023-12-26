const path = require('path')

module.exports = {
	publicPath: './',
	outputDir: './target/view',
	productionSourceMap: false,
	configureWebpack: {
		devtool: 'source-map',
		resolve: {
			alias: {
				'@': path.resolve(__dirname, './src/main/vue')
			}
		},
		output: {
			filename: 'js/[name].js',
			chunkFilename: 'js/[id].js'
		}
	},
	css: {
		extract: {
			filename: 'css/[name].css',
			chunkFilename: 'css/[id].css'
		}
	},
	pages: {
		index: {
			// entry for the page
			entry: './src/main/vue/main.js',
			// the source template
			template: 'public/index.html',
			// output as view/index.html
			filename: 'index.html',
			// when using title option,
			// template title tag needs to be <title><%= htmlWebpackPlugin.options.title %></title>
			title: 'gyomu-kanri',
			// chunks to include on this page, by default includes
			// extracted common chunks and vendor chunks.
			chunks: ['chunk-vendors', 'chunk-common', 'index']
		}
	},
	// 画像ファイルの容量制限設定（現在の設定値：6KB）
	chainWebpack: function(config) {
		config.module
		.rule('images')
		.use('url-loader')
		.options({limit: 6 * 1024})
	}
}