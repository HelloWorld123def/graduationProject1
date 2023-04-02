<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','store_name') || $check_field('add','store_name') || $check_field('set','store_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="店铺名称" prop="store_name">
												<el-input id="store_name" v-model="form['store_name']" placeholder="请输入店铺名称"
							  v-if="user_group === '管理员' || (form['purchase_information_id'] && $check_field('set','store_name')) || (!form['purchase_information_id'] && $check_field('add','store_name'))" :disabled="disabledObj['store_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','store_name')">{{form['store_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','merchant_information') || $check_field('add','merchant_information') || $check_field('set','merchant_information')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="商户信息" prop="merchant_information">
													<el-select v-if="user_group === '管理员' || (form['purchase_information_id'] && $check_field('set','merchant_information')) || (!form['purchase_information_id'] && $check_field('add','merchant_information'))" id="merchant_information" v-model="form['merchant_information']" :disabled="disabledObj['merchant_information_isDisabled']">
							<el-option v-for="o in list_user_merchant_information" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','merchant_information')" id="merchant_information" v-model="form['merchant_information']" :disabled="true">
							<el-option v-for="o in list_user_merchant_information" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','trade_name') || $check_field('add','trade_name') || $check_field('set','trade_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="商品名称" prop="trade_name">
												<el-input id="trade_name" v-model="form['trade_name']" placeholder="请输入商品名称"
							  v-if="user_group === '管理员' || (form['purchase_information_id'] && $check_field('set','trade_name')) || (!form['purchase_information_id'] && $check_field('add','trade_name'))" :disabled="disabledObj['trade_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','trade_name')">{{form['trade_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','commodity_price') || $check_field('add','commodity_price') || $check_field('set','commodity_price')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="商品价格" prop="commodity_price">
												<el-input id="commodity_price" v-model="form['commodity_price']" placeholder="请输入商品价格"
							  v-if="user_group === '管理员' || (form['purchase_information_id'] && $check_field('set','commodity_price')) || (!form['purchase_information_id'] && $check_field('add','commodity_price'))" :disabled="disabledObj['commodity_price_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','commodity_price')">{{form['commodity_price']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','resident_information') || $check_field('add','resident_information') || $check_field('set','resident_information')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="住户信息" prop="resident_information">
																		<div v-if="user_group !== '管理员'">
							{{ get_user_session_resident_information(form['resident_information']) }}
							<!--<el-input id="business_name" v-model="form['resident_information']" placeholder="请输入住户信息"-->
							<!--v-if="user_group === '管理员' || (form['purchase_information_id'] && $check_field('set','resident_information')) || (!form['purchase_information_id'] && $check_field('add','resident_information'))" :disabled="disabledObj['resident_information_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','resident_information')">{{form['resident_information']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['purchase_information_id'] && $check_field('set','resident_information')) || (!form['purchase_information_id'] && $check_field('add','resident_information'))" id="resident_information" v-model="form['resident_information']" :disabled="disabledObj['resident_information_isDisabled']">
								<el-option v-for="o in list_user_resident_information" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','resident_information')" id="resident_information" v-model="form['resident_information']" :disabled="true">
								<el-option v-for="o in list_user_resident_information" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="resident_information" v-model="form['resident_information']" :disabled="disabledObj['resident_information_isDisabled']">
							<el-option v-for="o in list_user_resident_information" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
																</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','resident_name') || $check_field('add','resident_name') || $check_field('set','resident_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="住户姓名" prop="resident_name">
												<el-input id="resident_name" v-model="form['resident_name']" placeholder="请输入住户姓名"
							  v-if="user_group === '管理员' || (form['purchase_information_id'] && $check_field('set','resident_name')) || (!form['purchase_information_id'] && $check_field('add','resident_name'))" :disabled="disabledObj['resident_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','resident_name')">{{form['resident_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','contact_number') || $check_field('add','contact_number') || $check_field('set','contact_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="联系电话" prop="contact_number">
												<el-input id="contact_number" v-model="form['contact_number']" placeholder="请输入联系电话"
							  v-if="user_group === '管理员' || (form['purchase_information_id'] && $check_field('set','contact_number')) || (!form['purchase_information_id'] && $check_field('add','contact_number'))" :disabled="disabledObj['contact_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','contact_number')">{{form['contact_number']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','purchase_quantity') || $check_field('add','purchase_quantity') || $check_field('set','purchase_quantity')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="购买数量" prop="purchase_quantity">
								<el-input-number id="purchase_quantity" v-model.number="form['purchase_quantity']"
						v-if="user_group === '管理员' || (form['purchase_information_id'] && $check_field('set','purchase_quantity')) || (!form['purchase_information_id'] && $check_field('add','purchase_quantity'))" :disabled="disabledObj['purchase_quantity_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','purchase_quantity')">{{form['purchase_quantity']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','total_purchase_price') || $check_field('add','total_purchase_price') || $check_field('set','total_purchase_price')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="购买总价" prop="total_purchase_price">
												<el-input id="total_purchase_price" v-model="form['total_purchase_price']" placeholder="请输入购买总价"
							  v-if="user_group === '管理员' || (form['purchase_information_id'] && $check_field('set','total_purchase_price')) || (!form['purchase_information_id'] && $check_field('add','total_purchase_price'))"  @focus="set_total_purchase_price()" :disabled="disabledObj['total_purchase_price_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','total_purchase_price')">{{form['total_purchase_price']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','shipping_address') || $check_field('add','shipping_address') || $check_field('set','shipping_address')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="配送地址" prop="shipping_address">
								<el-input type="textarea" id="shipping_address" v-model="form['shipping_address']" placeholder="请输入配送地址"
						v-if="user_group === '管理员' || (form['purchase_information_id'] && $check_field('set','shipping_address')) || (!form['purchase_information_id'] && $check_field('add','shipping_address'))" :disabled="disabledObj['shipping_address_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','shipping_address')">{{form['shipping_address']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','purchase_remarks') || $check_field('add','purchase_remarks') || $check_field('set','purchase_remarks')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="购买备注" prop="purchase_remarks">
								<el-input type="textarea" id="purchase_remarks" v-model="form['purchase_remarks']" placeholder="请输入购买备注"
						v-if="user_group === '管理员' || (form['purchase_information_id'] && $check_field('set','purchase_remarks')) || (!form['purchase_information_id'] && $check_field('add','purchase_remarks'))" :disabled="disabledObj['purchase_remarks_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','purchase_remarks')">{{form['purchase_remarks']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','order_status') || $check_field('add','order_status') || $check_field('set','order_status')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="订单状态" prop="order_status">
								<el-select id="order_status" v-model="form['order_status']"
						v-if="user_group === '管理员' || (form['purchase_information_id'] && $check_field('set','order_status')) || (!form['purchase_information_id'] && $check_field('add','order_status'))">
						<el-option v-for="o in list_order_status" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','order_status')">{{form['order_status']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','delivery_information') || $check_field('add','delivery_information') || $check_field('set','delivery_information')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="配送信息" prop="delivery_information">
								<el-input type="textarea" id="delivery_information" v-model="form['delivery_information']" placeholder="请输入配送信息"
						v-if="user_group === '管理员' || (form['purchase_information_id'] && $check_field('set','delivery_information')) || (!form['purchase_information_id'] && $check_field('add','delivery_information'))" :disabled="disabledObj['delivery_information_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','delivery_information')">{{form['delivery_information']}}</div>
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
				field: "purchase_information_id",
				url_add: "~/api/purchase_information/add?",
				url_set: "~/api/purchase_information/set?",
				url_get_obj: "~/api/purchase_information/get_obj?",
				url_upload: "~/api/purchase_information/upload?",

				query: {
					"purchase_information_id": 0,
				},

				form: {
								"store_name":  '', // 店铺名称
										"merchant_information": 0, // 商户信息
										"trade_name":  '', // 商品名称
										"commodity_price":  '', // 商品价格
										"resident_information": 0, // 住户信息
										"resident_name":  '', // 住户姓名
										"contact_number":  '', // 联系电话
										"purchase_quantity":  0, // 购买数量
										"total_purchase_price":  '', // 购买总价
										"shipping_address":  '', // 配送地址
										"purchase_remarks":  '', // 购买备注
										"order_status":  '', // 订单状态
										"delivery_information":  '', // 配送信息
											"purchase_information_id": 0, // ID
						
				},
				disabledObj:{
								"store_name_isDisabled": false,
										"merchant_information_isDisabled": false,
										"trade_name_isDisabled": false,
										"commodity_price_isDisabled": false,
										"resident_information_isDisabled": false,
										"resident_name_isDisabled": false,
										"contact_number_isDisabled": false,
					          			"purchase_quantity_isDisabled": false,
										"total_purchase_price_isDisabled": false,
										"shipping_address_isDisabled": false,
										"purchase_remarks_isDisabled": false,
										"order_status_isDisabled": false,
										"delivery_information_isDisabled": false,
										},

	
		
					// 用户列表
				list_user_merchant_information: [],
				
		
		
					// 用户列表
				list_user_resident_information: [],
						// 用户组
				group_user_resident_information: "",
				
		
		
		
		
		
						// 订单状态选项列表
				list_order_status: ['配货中','配送中','已完成'],
	
		
	
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
			 * 获取住户用户用户列表
			 */
			async get_list_user_resident_information() {
                // if(this.user_group !== "管理员" && this.form["resident_information"] === 0) {
                //     this.form["resident_information"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=住户用户");
                if(json.result && json.result.list){
                    this.list_user_resident_information = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					/**
			 * 获取住户用户用户组
			 */
			async get_group_user_resident_information() {
							this.form["resident_information"] = this.user.user_id;
							var json = await this.$get("~/api/user_group/get_obj?name=住户用户");
				if(json.result && json.result.obj){
					this.group_user_resident_information = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_resident_information(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_resident_information.source_table+"/get_obj?"
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
												_this.form["resident_information"] = id
									_this.disabledObj['resident_information' + '_isDisabled'] = true
						for (var i=0;i<arr.length;i++){
						  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
							for (var j = 0; j < arrForm.length; j++) {
							  if (arr[i] === arrForm[j]) {
								if (arr[i] !== "resident_information") {
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
					get_user_resident_information(id){
				var obj = this.list_user_resident_information.getObj({"user_id":id});
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
			
	
			
	
			
	
			
	
										set_total_purchase_price(){
				this.form.total_purchase_price = parseFloat(this.form.commodity_price) * parseFloat(this.form.purchase_quantity)
			},
							
	
			
	
			
	
			
	
		
			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
									// 获取缓存数据附加
				form = $.db.get("form");
							$.push(this.form ,form);
													
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
					bl = this.$check_action('/purchase_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/purchase_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/purchase_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/purchase_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/purchase_information/view','get');
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
												this.get_list_user_resident_information();
					this.get_group_user_resident_information();
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
