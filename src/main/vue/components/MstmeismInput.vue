<!--
//================================================================================
// ID: -
// ProjectName: ホストリプレイスプロジェクト
// SystemName: 業務管理
// VueName: MstmeismInput
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/01 |  AMC                 | 新規作成
//================================================================================
 -->
 
<template>
	<div class = "mstmeism-input">
		<div class = "input-float">
			<input
				type="text"
				class="code"
				:style="codeShowWidth"
				id="douteki"
				autocomplete="off"
				v-model="showValue"
				v-bind="$attrs"
				v-bind:maxLength="codeMaxLength"
				v-bind:class="['ime-inactive', {'contents--error-item': isError}, {'contents--require-item':!disabledFlg && requireFlg}]"
				v-bind:readonly="disabledFlg"
				v-bind:tabindex="tabindex"
				v-bind:disabled="disabledFlg"
				v-on:input="inputCodeInput($event)"
				v-on:keydown.delete="keydownDeleteControl"
				v-on:keydown.enter="blurCodeInput"
				v-on:keyup="keyupCodeInput($event)"
				v-on:blur="blurCodeInput"
				v-on:change="changeCodeInput($event.target.value)"
				v-on:focus="focusCodeInput"
				ref="codeInput"
			> 
		</div>
		<div class = "label-float">
			<label 
				class="label" 
				:style="labelShowWidth"
			>
				{{disp.kjnm}}
			</label>
		</div>
	</div>
</template>

<script>
import { co_post } from '@/common/ajaxUtility.js';
import { co_getMessage } from '@/common/messageUtility.js';
import { API_MSTMEISM_INPUT } from '@/const/route.js';
import { co_trim, co_fullStrToHalfStr } from '@/common/stringUtility.js';
import { co_checkHalfNumber, co_checkDigitEqualed, co_isEmpty } from '@/common/checkUtility.js';
import { STORE_ERROR_ADDMSG, STORE_ERROR_IS_ERRFLG } from '@/const/store.js';


export default {
	props: {
		tabindex: {type: String, default: '0'},							// タブインデックス
		name: {type: String, default: ''},									// 項目名
		requireFlg: {type: Boolean, default: false},						// 必須チェックの有無
		disabledFlg: {type: Boolean, default: false},						// true:非活性、false:活性
		recKb: {type: String, default: ''},								// Componentで使う、遷移元画面のレコード区分
		modelValue: {type: String, default: ''},							// 入力値
		activeZeroPadding: {type: Boolean, default: true},					// true: パディングあり, false: パディングなし
		codeMaxLength: {type: Number,default: 1},							// 最大桁数(コ―ド)
		codeShowLength: {type: Number, default: 1},						// 表示桁数(コ―ド)
		labelShowLength: {type: Number, default: 1},						// 表示桁数(リストボックス)
		listItems: {type: Array, default: function() {					// リストボックスに設定するObject
			return []
		}},
		emptyMessage: {type: String, default:  ''},						// 明細情報がnull時の出力メッセージ
	},
	emits: ['blur', 'focus', 'change', 'update:modelValue'],
	data: function() {
		return {
			disp: {
				preCode: '',				// 前コード
				mapObj:null,				// 名称情報
				kjnm:'',					// 漢字名称
				ketasuu:0,					// 名称コード桁数
			},
			form: {
				listItems: this.listItems,  // リストオブジェクト
			},
			showValue: null,
			isError: false,					// エラー判定
			isChangedValue: false,			// 変更制御フラグ
		}
	},
	
	created: async function() {
		// DBからデータを取得
		if (co_isEmpty(this.listItems)) {
			this.createList(await this.getData());
		} else {
			this.createList(await JSON.parse(JSON.stringify(this.listItems)));
		}
	},
	
	mounted: function() {
	},
	computed: {

		// コ―ドの表示幅
		codeShowWidth() {
			return {
				// 計算式：フォントサイズ(px) * 表示桁数 + (padding幅 + border幅) * 2(左右)
				'--code-show-width': String(13 * this.codeShowLength + (10 + 2) * 2) + 'px'
			}
		},

		// ラベルの表示幅
		labelShowWidth() {
			return {
				// 計算式：フォントサイズ(px) * 表示桁数 + (padding幅 + border幅) * 2(左右)
				'--label-show-width': String(18 * this.labelShowLength) + 'px'
			}
		},

		// エラー状態管理
		errFlg: function() {
			return this.$store.getters[STORE_ERROR_IS_ERRFLG];
		}
	},
	watch: {
		modelValue: function(val) {
			this.showValue = ( val );
			this.showText( val );
		}, 
	},
	methods: {
		keydownDeleteControl: function (e) {
			//活性の時のみ処理
			if(!this.disabledFlg){
				//活性時は親へのイベントを抑止する
				//（親でイベントをキャンセルしている）
				e.stopPropagation();
			}
		},

		// コードマスタからリストの値を取得
		getData: function() {
			// ajaxにてサーバーと通信
			let params = { reckb: this.recKb };
			return co_post( API_MSTMEISM_INPUT, params );
		},

		// リスト作成
		createList: function( val ) {
			let mapObj = new Map();
			// 明細コードの桁数を取得
			this.disp.ketasuu = this.codeMaxLength;	//val[0].code.length;
			let size = 30 + this.disp.ketasuu * 10;
			var elem = document.getElementById("douteki");
			// 表示幅を明細コードに合わせて調整
			elem.style.width = size + "px";
			
			for (let i = 0; i < val.length; i++) {
				mapObj.set(val[i].code, val[i].name);	
			}
			this.disp.mapObj = mapObj;
		},

		// 漢字名称表示
		showText: function( val ) {

			this.clearError();

			let keyVal = this.disp.preCode.concat(val);
			
			// 入力値を取得
			const data = this.disp.mapObj.get(keyVal);
			
			// 入力形式チェック（属性名が「数字のみ」）
			if (!co_checkHalfNumber(val)) {
				val = '';
			} else {
				// 入力値が全角数字だった場合、半角数字に変換
				val = co_fullStrToHalfStr(val)
			}
			
			// 入力値チェック
			// 指定桁数で入力されてる場合
			if (co_checkDigitEqualed(val, this.disp.ketasuu)) {

				// コードと一致しない場合「該当なし」メッセージを表示
				if (data == null) {
					this.disp.kjnm = this.emptyMessage;
					this.setError();
				// コードと一致している場合、漢字名称を表示
				} else {
					this.disp.kjnm = data;
				}

			// 指定桁数以外の場合、漢字名称をクリア
			} else {
				this.disp.kjnm = '';
			}
			this.$emit('change');
		},

		// フォーカス時イベント
		focusCodeInput: async function() {
			this.$emit("focus");
			
			// 画面の描画を待機
			await this.$nextTick();
			
			// テキストボックスを全選択
			this.$refs.codeInput.select();
		},

		// フォーカスアウト時イベント
		blurCodeInput: function( event ) {
			let value = co_trim(event.target.value);

			// 入力形式チェック（属性名が「数字のみ」）
			if (!co_checkHalfNumber(value)) {
				this.showValue = '';
			}

			// パディング処理
			if ( value != "" ) {
				value = this.doZeroPadding( value );
				this.showValue = value;
			}else{
				this.showValue = '';
			}
			
			this.$nextTick(function() {
				this.$emit('update:modelValue', this.showValue);
			});

			if ( this.isChangedValue ) {
				this.$emit('change');
				this.isChangedValue = false;
			}

			this.$emit("blur");
		},
		
		// 0埋め
		doZeroPadding: function( val ) {
			let ketasuu = this.disp.ketasuu;
			if(val.length < ketasuu) {
				return (Array(ketasuu).join('0') + val).slice(-ketasuu);
			} else {
				return val
			}
		},

		// テキストボックスの入力制御
		limitOnlyNumber: function ( e ) {
			// 全角文字確定後のみ、数値以外の値をトリムする
			if ( e.isComposing ) {
				return
			} else {
				e.target.value = e.target.value.replace(/[０-９]/g, 
					function(s) {
						return String.fromCharCode(s.charCodeAt(0) - 0xFEE0);
					});
				e.target.value = e.target.value.replace(/\D/g, '');
			}
		},

		setPreVal: function (val) {
			this.disp.preCode = val;
			this.showText(this.modelValue);
		},

		// 入力時イベント
		inputCodeInput: function( e ) {
			this.limitOnlyNumber( e );
			
			this.$emit('update:modelValue', e.target.value);
		},
		
		// キーアップ時イベント
		keyupCodeInput: function ( e ) {
			this.limitOnlyNumber( e );
		},
		
		// チェンジ時イベント
		changeCodeInput: function( val ) {
			this.isChangedValue = true;
			this.$emit('update:modelValue', val);

			this.$nextTick(function() {
				this.$emit('update:modelValue', val);
				this.$emit('change');
			});
		},
		// ▼ ---------------------------------------- 共有メソッド ---------------------------------------- ▼
		// 妥当性チェック
		validate: function() {

			// エラーの初期化
			this.clearError();

			let value = co_trim(this.modelValue.toString());
			
			// 必須チェック
			if(this.requireFlg) {
				if ( co_isEmpty(value) ) {
					this.$store.commit(STORE_ERROR_ADDMSG, co_getMessage('CMS0013E', this.name));
					this.setError();
					return
				}
			}

			if ( !co_isEmpty(value) ) {
				// 候補値以外の値
				if (this.disp.kjnm == this.emptyMessage) {
					this.$store.commit(STORE_ERROR_ADDMSG,
						co_getMessage('CMS0046E', this.name));
					this.setError();
				}
				
				// -- コードテキストチェック --
				// コード（数字）入力チェック
				if(!(co_checkDigitEqualed(value, this.codeMaxLength))) {
					this.$store.commit(STORE_ERROR_ADDMSG,
						co_getMessage('CMS0017E', this.name, String(this.codeMaxLength)));
					this.setError();
				}

				// 入力形式チェック（属性名が「数字のみ」）
				if (!co_checkHalfNumber(value)) {
					this.$store.commit(STORE_ERROR_ADDMSG,
						co_getMessage('CMS0010E', this.name));
					this.setError();
				}
			}
			
			this.showText( this.modelValue )
		},
		// エラー設定
		setError: function() {
			this.isError = true;
		},
		// エラー表示消去
		clearError: function() {
			this.isError = false;
		},
		// フォーカス設定
		focus: function() {
			this.$refs.codeInput.focus();
		}
	}
}
</script>

<style scoped>
	.mstmeism-input{
		display: flex;
	}
	
	.code {
		width: var(--code-show-width);
	}

	.label-float {
		margin-top: 6px;
		text-align: left;
		margin-left: 5px;
	}
	
	.label {
		width: var(--label-show-width);
		white-space: nowrap;
		overflow: hidden;
		display: inline-block;
	}
</style>