<!--
//================================================================================
// ID: -
// ProjectName: コープ九州事業連合様  ホストシステムリプレイス
// SystemName: 業務管理
// VueName: FileUpload
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2022/10/28 |   AMC                | 新規作成
//================================================================================
 -->
<template>
	<div class="div-item--upload" v-bind:style="styles">
		<select
				size="1"
				v-on:dragenter="dragEnter"
				v-on:dragleave="dragLeave"
				v-on:dragover.prevent
				v-on:drop.prevent="dropFile"
				v-bind:class="{'enter': hoverFlg, 'contents--error-item': isError}"
				v-bind:disabled="disabledFlg"
				v-bind:tabindex="tabindex"
				ref="selectFile"
			>
				<option>{{ fileName }}</option>
		</select>
		<button-input
				class="contents__btn-blue btn-2-row"
				v-bind:value="'ファイルを\n選択'"
				v-bind:disabled="disabledFlg"
				v-bind:tabindex="Number(tabindex) <= 0 ? tabindex:String(Number(tabindex) + 1)"
				v-on:click="clickAddBtn"></button-input>
		<button-input
			class="contents__btn-blue btn-2-row"
			v-bind:value="'ファイルを\n解除'"
			v-bind:disabled="disabledFlg"
			v-bind:tabindex="Number(tabindex) <= 0 ? tabindex:String(Number(tabindex) + 2)"
			v-on:click="clickDelBtn"></button-input>
		<input
			style="display: none"
			ref="inputFile"
			type="file"
			v-on:click="clearFile"
			v-on:change="selectedFile" />
	</div>
</template>

<script>
import ButtonInput from '@/components/ButtonInput.vue';

import { co_isEmpty } from '@/common/checkUtility.js';
import { co_getMessage } from '@/common/messageUtility.js';

import { FILE_SIZE } from '@/const/const.js';
import {
	STORE_ERROR_ADDMSG,
	STORE_ERROR_IS_ERRFLG,
	STORE_ERROR_POP_ERRMSG,
} from '@/const/store.js';

export default {
	props: {
		name: {type: String, default: ''},							// 項目名
		requireFlg: {type: Boolean, default: false},				// true : 必須
		disabledFlg: { type: Boolean, default: false },			// 活性・非活性フラグ
		messageType: {type: String, default: ''},					// エラーメッセージの引数
		fileSize: {Number, default: 0},							// ファイルサイズ
		fileExtensions: {type: Array, default: function() {
				return []
			}
		},															// 許可するファイルの拡張子
		tabindex: {type: String, default: '0'},					// タブインデックス
	},
	components: {
		'button-input': ButtonInput
	},
	data: function() {
		return {
			hoverFlg: false,
			isError: false,
			fileNotFoundCount:0, 
			file: null,
			fileName: '',
			formData: null,
		}
	},
	created: function() {

	},
	computed: {
		styles: function() {
			let backgroundColor = 'azure';
			if (this.requireFlg) { 
				backgroundColor = '#FCFFE1';
			}
			if (this.disabledFlg) {
				backgroundColor =  '#F0F0F0'
			}

			return {
				'--back-ground-color': backgroundColor,
			}
		},	
		errFlg: function() {
			return this.$store.getters[STORE_ERROR_IS_ERRFLG];
		}
	},
	watch: {
		disabledFlg: function( val ) {
			if( val ) {
				this.clearError();
			}
		},
		errFlg: function(newValue) {
			if(newValue === false) {
				this.clearError();
			}
		},
		fileName: function(val) {
			if (co_isEmpty(val)) {
				return;
			}
			
			this.fileNotFoundCount = 0;
		}
	},
	methods: {
		dragEnter() {
			this.hoverFlg = true;
		},
		dragLeave() {
			this.hoverFlg = false;
		},
		dropFile() {
			this.hoverFlg = false;

			this.file = Object.assign(event.dataTransfer.files[0]);
			this.fileName = this.file.name;
		},
		clickAddBtn: function() {

			// 選択ダイアログ呼び出し
			this.$refs.inputFile.click();
		},
		clickDelBtn: function() {

			if ( this.fileNotFoundCount >= 1) {
				return
			}
			
			this.fileNotFoundCount = 0;
			if(this.file === null) {
				this.$store.commit(STORE_ERROR_ADDMSG,
					co_getMessage('CMS0014E', 'ファイル'));
				this.fileNotFoundCount += 1;
				this.isError = true;
				return
			}
			
			this.$store.commit(STORE_ERROR_POP_ERRMSG, co_getMessage('CMS0014E', 'ファイル'));
			this.isError = false;

			// セレクトボックスで選択したファイルを削除
			this.file = null;
			this.fileName = "";

		},
		clearFile: function(event) {
			event.target.value = '';
		},
		selectedFile: function(event) {
			if (!co_isEmpty(event.target.value)) {
				// 選択ダイアログで選択したファイルを取得
				this.file = this.$refs.inputFile.files[0];
				this.fileName = this.file.name;
			}
		},
		createFormData: function( key ) {

			this.formData = new FormData();
			if ( this.file !== null ) {
				this.formData.append( key, this.file );
			}
		},
		// 妥当性チェック
		validate: function() {

			let error = false;
			this.fileNotFoundCount = 0;

			// 必須チェック
			if(this.requireFlg === true) {
				if(this.file === null) {
					this.$store.commit(STORE_ERROR_ADDMSG,
						co_getMessage('CMS0013E', this.name));
					this.fileNotFoundCount += 1;
					error = true;
				}
			}

			if(this.file !== null) {

				let limit = FILE_SIZE.ONE_MB * this.fileSize

				// ファイルサイズチェック
				if(limit < this.file.size) {
					this.$store.commit(STORE_ERROR_ADDMSG,
						co_getMessage('CMS0030E', this.name , this.fileSize.toString() + 'MB'));
					error = true;
				}

				// 拡張子チェック
				// ※拡張子の指定があり、かつ、拡張子エラーがない場合のみ
				if (!co_isEmpty(this.fileExtensions)) {
					// ファイル名から拡張子を取得し、比較のため小文字にする
					let ext = this.getExtension(this.fileName).toLowerCase();

					// 許可された拡張子かチェック
					let result = this.fileExtensions.indexOf(ext);
					if(result === -1) {
						this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('CMS0028E', this.messageType));
						error = true;
					}
				}
			}

			this.isError = error;
			return !this.isError;
		},
		getExtension: function(filename) {

			var pos = filename.lastIndexOf('.');
			if (pos === -1){
				return '';
			}
			return filename.slice(pos + 1);
		},
		getFile: function() {
			return this.file;
		},
		getFormData: function() {
			return this.formData;
		},
		// エラー設定
		setError: function() {
			this.isError = true;
		},
		// エラークリア
		clearError: function() {
			this.isError = false;
		},
		// フォーカス設定
		focus: function() {
			this.$refs.selectFile.focus();
		},
		// 選択済みファイルクリア
		clearSelectedFile() {
			this.file = null;
			this.fileName = "";
		}
	}
}
</script>

<style scoped>
.btn-2-row {
	font-size: 16px;
	line-height: 1.15em;
    padding: 0px 16px;
}

.enter {
	background-color: #5e74db !important;
	color: #fff;
}

.div-item--upload > * {
	margin-right: 10px;
}

.div-item--upload select::-ms-expand{
	display: none;
}

.div-item--upload select {
	width: 550px;
	background-color: var(--back-ground-color);
	appearance: none;
}

.div-item--upload select:disabled {
	background: #f0f0f0;
}
</style>