<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','store_name') || $check_field('add','store_name') || $check_field('set','store_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="店铺名称" prop="store_name">
												<el-input id="store_name" v-model="form['store_name']" placeholder="请输入店铺名称"
							  v-if="user_group === '管理员' || (form['product_information_id'] && $check_field('set','store_name')) || (!form['product_information_id'] && $check_field('add','store_name'))" :disabled="disabledObj['store_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','store_name')">{{form['store_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','merchant_information') || $check_field('add','merchant_information') || $check_field('set','merchant_information')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="商户信息" prop="merchant_information">
																		<div v-if="user_group !== '管理员'">
							{{ get_user_session_merchant_information(form['merchant_information']) }}
							<!--<el-input id="business_name" v-model="form['merchant_information']" placeholder="请输入商户信息"-->
							<!--v-if="user_group === '管理员' || (form['product_information_id'] && $check_field('set','merchant_information')) || (!form['product_information_id'] && $check_field('add','merchant_information'))" :disabled="disabledObj['merchant_information_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','merchant_information')">{{form['merchant_information']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['product_information_id'] && $check_field('set','merchant_information')) || (!form['product_information_id'] && $check_field('add','merchant_information'))" id="merchant_information" v-model="form['merchant_information']" :disabled="disabledObj['merchant_information_isDisabled']">
								<el-option v-for="o in list_user_merchant_information" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','merchant_information')" id="merchant_information" v-model="form['merchant_information']" :disabled="true">
								<el-option v-for="o in list_user_merchant_information" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="merchant_information" v-model="form['merchant_information']" :disabled="disabledObj['merchant_information_isDisabled']">
							<el-option v-for="o in list_user_merchant_information" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
																</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','trade_name') || $check_field('add','trade_name') || $check_field('set','trade_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="商品名称" prop="trade_name">
												<el-input id="trade_name" v-model="form['trade_name']" placeholder="请输入商品名称"
							  v-if="user_group === '管理员' || (form['product_information_id'] && $check_field('set','trade_name')) || (!form['product_information_id'] && $check_field('add','trade_name'))" :disabled="disabledObj['trade_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','trade_name')">{{form['trade_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','commodity_price') || $check_field('add','commodity_price') || $check_field('set','commodity_price')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="商品价格" prop="commodity_price">
								<el-input-number id="commodity_price" v-model.number="form['commodity_price']"
						v-if="user_group === '管理员' || (form['product_information_id'] && $check_field('set','commodity_price')) || (!form['product_information_id'] && $check_field('add','commodity_price'))" :disabled="disabledObj['commodity_price_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','commodity_price')">{{form['commodity_price']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','product_picture') || $check_field('add','product_picture') || $check_field('set','product_picture')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="商品图片" prop="product_picture">
								<el-upload :disabled="disabledObj['product_picture_isDisabled']" id="product_picture" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_product_picture"
						:show-file-list="false" v-if="user_group === '管理员' || (form['product_information_id'] && $check_field('set','product_picture')) || (!form['product_information_id'] && $check_field('add','product_picture'))">
						<img v-if="form['product_picture']" :src="$fullUrl(form['product_picture'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','product_picture')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['product_picture'])" :preview-src-list="[$fullUrl(form['product_picture'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','product_introduction') || $check_field('add','product_introduction') || $check_field('set','product_introduction')" :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="商品介绍" prop="product_introduction">
					<quill-editor v-model.number="form['product_introduction']"
						v-if="user_group === '管理员' || (form['product_information_id'] && $check_field('set','product_introduction')) || (!form['product_information_id'] && $check_field('add','product_introduction')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','product_introduction')" v-html="form['product_introduction']"></div>
				</el-form-item>
			</el-col>
					
	
	
	
	
	
	
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "product_information_id",
				url_add: "~/api/product_information/add?",
				url_set: "~/api/product_information/set?",
				url_get_obj: "~/api/product_information/get_obj?",
				url_upload: "~/api/product_information/upload?",

				query: {
					"product_information_id": 0,
				},

				form: {
								"store_name":  '', // 店铺名称
										"merchant_information": 0, // 商户信息
										"trade_name":  '', // 商品名称
										"commodity_price":  0, // 商品价格
										"product_picture":  '', // 商品图片
										"product_introduction":  '', // 商品介绍
											"product_information_id": 0, // ID
						
				},
				disabledObj:{
								"store_name_isDisabled": false,
										"merchant_information_isDisabled": false,
										"trade_name_isDisabled": false,
					          			"commodity_price_isDisabled": false,
										"product_picture_isDisabled": false,
										"product_introduction_isDisabled": false,
										},

	
		
					// 用户列表
				list_user_merchant_information: [],
						// 用户组
				group_user_merchant_information: "",
				
		
		
		
	
			}
		},
		methods: {


	
	
			
	
				/**
			 * 获取商户用户用户列表
			 */
			async get_list_user_merchant_information() {
                // if(this.user_group !== "管理员" && this.form["merchant_information"] === 0) {
                //     this.form["merchant_information"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=商户用户");
                if(json.result && json.result.list){
                    this.list_user_merchant_information = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					/**
			 * 获取商户用户用户组
			 */
			async get_group_user_merchant_information() {
							this.form["merchant_information"] = this.user.user_id;
							var json = await this.$get("~/api/user_group/get_obj?name=商户用户");
				if(json.result && json.result.obj){
					this.group_user_merchant_information = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_merchant_information(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_merchant_information.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
									for (let key in _this.form) {
							arrForm.push(key)
						}
												_this.form["merchant_information"] = id
									_this.disabledObj['merchant_information' + '_isDisabled'] = true
						for (var i=0;i<arr.length;i++){
						  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
							for (var j = 0; j < arrForm.length; j++) {
							  if (arr[i] === arrForm[j]) {
								if (arr[i] !== "merchant_information") {
			                      _this.form[arrForm[j]] = res.result.obj[arr[i]]
			                      _this.disabledObj[arrForm[j] + '_isDisabled'] = true
								  break;
								} else {
								  _this.disabledObj[arrForm[j] + '_isDisabled'] = true
								}
							  }
							}
						  }
						}
					}
				});
			},
					get_user_merchant_information(id){
				var obj = this.list_user_merchant_information.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			
	
			
	
						/**
			 * 上传商品图片
			 * @param {Object} param 图片参数
			 */
			upload_product_picture(param){
						this.uploadFile(param.file, "product_picture");
					},
	
	
			
	
		
			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
													
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
																$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){


																		

			},


			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/product_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/product_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/product_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/product_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/product_information/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
							this.get_list_user_merchant_information();
					this.get_group_user_merchant_information();
													},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}




</style>
