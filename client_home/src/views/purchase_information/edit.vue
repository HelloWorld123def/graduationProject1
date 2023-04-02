<template>
	<div class="diy_edit page_purchase_information" id="purchase_information_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
						<div v-if="$check_field('set','store_name') || $check_field('add','store_name') || $check_field('get','store_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								店铺名称:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_store_name" v-model="form['store_name']" placeholder="请输入店铺名称" v-if="(form['store_name'] && $check_field('set','store_name')) || (!form['store_name'] && $check_field('add','store_name'))"  :disabled="disabledObj['store_name_isDisabled']"/>
							<span v-else-if="$check_field('get','store_name')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','merchant_information') || $check_field('add','merchant_information') || $check_field('get','merchant_information')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								商户信息:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_merchant_information" :disabled="disabledObj['merchant_information_isDisabled']" v-model="form['merchant_information']" v-if="(form['merchant_information'] && $check_field('set','merchant_information')) || (!form['merchant_information'] && $check_field('add','merchant_information'))" >
								<option v-for="o in list_user_merchant_information" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','merchant_information')">{{ form['merchant_information'] }}</span>
						</div>
					</div>
							<div v-if="$check_field('set','trade_name') || $check_field('add','trade_name') || $check_field('get','trade_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								商品名称:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_trade_name" v-model="form['trade_name']" placeholder="请输入商品名称" v-if="(form['trade_name'] && $check_field('set','trade_name')) || (!form['trade_name'] && $check_field('add','trade_name'))"  :disabled="disabledObj['trade_name_isDisabled']"/>
							<span v-else-if="$check_field('get','trade_name')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','commodity_price') || $check_field('add','commodity_price') || $check_field('get','commodity_price')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								商品价格:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_commodity_price" v-model="form['commodity_price']" placeholder="请输入商品价格" v-if="(form['commodity_price'] && $check_field('set','commodity_price')) || (!form['commodity_price'] && $check_field('add','commodity_price'))"  :disabled="disabledObj['commodity_price_isDisabled']"/>
							<span v-else-if="$check_field('get','commodity_price')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','resident_information') || $check_field('add','resident_information') || $check_field('get','resident_information')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								住户信息:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_resident_information" :disabled="disabledObj['resident_information_isDisabled']" v-model="form['resident_information']" v-if="(form['resident_information'] && $check_field('set','resident_information')) || (!form['resident_information'] && $check_field('add','resident_information'))" >
								<option v-for="o in list_user_resident_information" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','resident_information')">{{ form['resident_information'] }}</span>
						</div>
					</div>
							<div v-if="$check_field('set','resident_name') || $check_field('add','resident_name') || $check_field('get','resident_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								住户姓名:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_resident_name" v-model="form['resident_name']" placeholder="请输入住户姓名" v-if="(form['resident_name'] && $check_field('set','resident_name')) || (!form['resident_name'] && $check_field('add','resident_name'))"  :disabled="disabledObj['resident_name_isDisabled']"/>
							<span v-else-if="$check_field('get','resident_name')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','contact_number') || $check_field('add','contact_number') || $check_field('get','contact_number')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								联系电话:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_contact_number" v-model="form['contact_number']" placeholder="请输入联系电话" v-if="(form['contact_number'] && $check_field('set','contact_number')) || (!form['contact_number'] && $check_field('add','contact_number'))"  :disabled="disabledObj['contact_number_isDisabled']"/>
							<span v-else-if="$check_field('get','contact_number')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','purchase_quantity') || $check_field('add','purchase_quantity') || $check_field('get','purchase_quantity')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								购买数量:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_purchase_quantity" v-model="form['purchase_quantity']" placeholder="请输入购买数量" v-if="(form['purchase_quantity'] && $check_field('set','purchase_quantity')) || (!form['purchase_quantity'] && $check_field('add','purchase_quantity'))"  :disabled="disabledObj['purchase_quantity_isDisabled']"/>
							<span v-else-if="$check_field('get','purchase_quantity')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','total_purchase_price') || $check_field('add','total_purchase_price') || $check_field('get','total_purchase_price')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								购买总价:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_total_purchase_price" v-model="form['total_purchase_price']" placeholder="请输入购买总价" v-if="(form['total_purchase_price'] && $check_field('set','total_purchase_price')) || (!form['total_purchase_price'] && $check_field('add','total_purchase_price'))"  @focus="set_total_purchase_price()" :disabled="disabledObj['total_purchase_price_isDisabled']"/>
							<span v-else-if="$check_field('get','total_purchase_price')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','shipping_address') || $check_field('add','shipping_address') || $check_field('get','shipping_address')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								配送地址:
							</span>
						</div>
								<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_shipping_address" v-model="form['shipping_address']" v-if="(form['shipping_address'] && $check_field('set','shipping_address')) || (!form['shipping_address'] && $check_field('add','shipping_address'))" :disabled="disabledObj['shipping_address_isDisabled']" />
							<span v-else-if="$check_field('get','shipping_address')">{{ form['${obj.name}'] }}</span>
						</div>
							</div>
							<div v-if="$check_field('set','purchase_remarks') || $check_field('add','purchase_remarks') || $check_field('get','purchase_remarks')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								购买备注:
							</span>
						</div>
								<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_purchase_remarks" v-model="form['purchase_remarks']" v-if="(form['purchase_remarks'] && $check_field('set','purchase_remarks')) || (!form['purchase_remarks'] && $check_field('add','purchase_remarks'))" :disabled="disabledObj['purchase_remarks_isDisabled']" />
							<span v-else-if="$check_field('get','purchase_remarks')">{{ form['${obj.name}'] }}</span>
						</div>
							</div>
							<div v-if="$check_field('set','order_status') || $check_field('add','order_status') || $check_field('get','order_status')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								订单状态:
							</span>
						</div>
								<!-- 选项 -->
						<div class="diy_field diy_down">
							<select id="form_order_status" v-model="form['order_status']" v-if="(form['order_status'] && $check_field('set','order_status')) || (!form['order_status'] && $check_field('add','order_status'))" >
								<option v-for="o in list_order_status" :value="o">
									{{o}}
								</option>
							</select>
							<span v-else-if="$check_field('get','order_status')">{{ form['order_status'] }}</span>
						</div>
							</div>
							<div v-if="$check_field('set','delivery_information') || $check_field('add','delivery_information') || $check_field('get','delivery_information')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								配送信息:
							</span>
						</div>
								<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_delivery_information" v-model="form['delivery_information']" v-if="(form['delivery_information'] && $check_field('set','delivery_information')) || (!form['delivery_information'] && $check_field('add','delivery_information'))" :disabled="disabledObj['delivery_information_isDisabled']" />
							<span v-else-if="$check_field('get','delivery_information')">{{ form['${obj.name}'] }}</span>
						</div>
							</div>
	




				</div>
				<div class="diy_edit_submit_box row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/purchase_information/get_obj?",
				url_add: "~/api/purchase_information/add?",
				url_set: "~/api/purchase_information/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
						"store_name": "",
							"merchant_information": 0,
							"trade_name": "",
							"commodity_price": "",
							"resident_information": 0,
							"resident_name": "",
							"contact_number": "",
							"purchase_quantity": 0,
							"total_purchase_price": "",
							"shipping_address": "",
							"purchase_remarks": "",
							"order_status": "",
							"delivery_information": "",
						"purchase_information_id": 0,
				},

				obj: {
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
						"purchase_information_id": 0,
				},

				// 表单字段
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
						"purchase_information_id": 0,
				},
				disabledObj:{
						"store_name_isDisabled": false,
							"merchant_information_isDisabled": false,
							"trade_name_isDisabled": false,
							"commodity_price_isDisabled": false,
							"resident_information_isDisabled": false,
							"resident_name_isDisabled": false,
							"contact_number_isDisabled": false,
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
																		// 订单状态选项列表
				list_order_status: ['配货中','配送中','已完成'],
				
				// ID字段
				field: "purchase_information_id",

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
					async get_user_session_resident_information(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=住户用户");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["resident_information"] = user_id
								_this.disabledObj['resident_information' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
                  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
                    for (var j = 0; j < arrForm.length; j++) {
                      if (arr[i] === arrForm[j]) {
                        if (arr[i] !== "resident_information") {
                          _this.form[arrForm[j]] = res.result.obj[arr[i]]
                          _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                          break;
                        }
                      }
                    }
                  }
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},
	
				
				
				
				
									set_total_purchase_price(){
				this.form.total_purchase_price = parseFloat(this.form.commodity_price) * parseFloat(this.form.purchase_quantity)
        let r = /^\+?[1-9][0-9]*$/; // 正整数
        if(!r.test(this.form.total_purchase_price) ){
          this.form.total_purchase_price = this.form.total_purchase_price.toFixed(2);
        }
			},
									
				
				
				
	
			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/purchase_information/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				// if (form) {
        //   delete(form.examine_state)
        //   delete(form.examine_reply)
        //   this.obj = $.push(this.obj ,form);
				// 	this.form = $.push(this.form ,form);
				// }
				// var arr = []
				// for (let key in form) {
				// 	arr.push(key)
				// }
				// for (var i=0;i<arr.length;i++){
				// 	this.disabledObj[arr[i] + '_isDisabled'] = true
				// }
        if (form) {
          var arr = []
          for (let key in form) {
            arr.push(key)
          }
          var arrForm = []
          for (let key in this.form) {
            arrForm.push(key)
          }
          for (var i=0;i<arr.length;i++){
            if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
              for (var j = 0; j < arrForm.length; j++) {
                if (arrForm[j] === arr[i]) {
                  this.form[arrForm[j]] = form[arr[i]]
                  this.obj[arrForm[j]] = form[arr[i]]
                  this.disabledObj[arrForm[j] + '_isDisabled'] = true
                  break;
                }
              }
            }
          }
        }
																										
        $.db.del("form");
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				// var form = $.db.get("form");
				// var obj = Object.assign({} ,form ,this.obj);
				// if (obj) {
        //   delete(obj.examine_state)
        //   delete(obj.examine_reply)
				// 	this.obj = $.push(this.obj ,obj);
				// }
				// if (form) {
        //   delete(form.examine_state)
        //   delete(form.examine_reply)
				// 	this.form = $.push(this.form ,form);
				// }
				if(func){
					func(json);
				}
			},

		},
		created() {
									this.get_list_user_merchant_information();
												this.get_user_session_resident_information();
					this.get_list_user_resident_information();
																											},
	}
</script>

<style>




</style>
