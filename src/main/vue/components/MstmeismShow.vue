<!--
//================================================================================
// ID: -
// ProjectName: ホストリプレイスプロジェクト
// SystemName: 業務管理
// VueName: MstmeismShow
//
// <<Modification History>>
// Version  | Date       | Updated By           | Content
// ---------+------------+----------------------+---------------------------------
// 01.00.00 | 2023/07/01 |  AMC                 | 新規作成
//================================================================================
 -->
 
<template>
	<div class = "mstmeism-show">
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
import { API_MSTMEISM_INPUT } from '@/const/route.js';
import { co_isEmpty } from '@/common/checkUtility.js';

export default {
	props: {
		recKb: {type: String, default: ''},								// Componentで使う、遷移元画面のレコード区分
		activeZeroPadding: {type: Boolean, default: true},					// true: パディングあり, false: パディングなし
		codeMaxLength: {type: Number,default: 1},							// 最大桁数(コ―ド)
		codeShowLength: {type: Number, default: 1},						// 表示桁数(コ―ド)
		labelShowLength: {type: Number, default: 1},						// 表示桁数(リストボックス)
		tabindex: {type: String, default: '0'},							// タブインデックス
		disabledFlg: {type: Boolean, default: false},						// true:非活性、false:活性
		emptyMessage: {type: String, default:  ''},						// 明細情報がnull時の出力メッセージ
		listItems: {type: Array, default: function() {					// リストボックスに設定するObject
			return []
		}},
	},
	emits: ['blur', 'focus', 'change'],
	data: function() {
		return {
			disp: {
				targetCode: '',				// 前コード
				mapObj:null,				// 名称情報
				kjnm:'',					// 漢字名称
				ketasuu:0,					// 名称コード桁数
			},
			form: {
				listItems: this.listItems,  // リストオブジェクト
			},
			inputCode:'',					// 入力値
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

		// ラベルの表示幅
		labelShowWidth() {
			return {
				// 計算式：フォントサイズ(px) * 表示桁数 + (padding幅 + border幅) * 2(左右)
				'--label-show-width': String(18 * this.labelShowLength) + 'px'
			}
		},
	},
	watch: {
		inputCode: function(val) {
			this.showText(val);
		}, 
	},
	methods: {
		keyControl: function (e) {
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

			// 入力値を取得
			const data = this.disp.mapObj.get(val);

			// コードと一致しない場合「該当なし」メッセージを表示
			if (data == null) {
				this.disp.kjnm = this.emptyMessage;
			// コードと一致している場合、漢字名称を表示
			} else {
				this.disp.kjnm = data;
			}

		},

		setVal: function (val) {
			this.showText(val);
		},

		// 入力時イベント
		inputCodeInput: function( e ) {
			this.limitOnlyNumber( e );
		},
		
		// キーアップ時イベント
		keyupCodeInput: function ( e ) {
			this.limitOnlyNumber( e );
		},
		
		// チェンジ時イベント
		changeCodeInput: function() {
			this.isChangedValue = true;
		},
		
		// エラー設定
		setError: function() {
			this.isError = true;
		},
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