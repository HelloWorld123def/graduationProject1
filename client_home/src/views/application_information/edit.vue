<template>
	<div class="diy_edit page_application_information" id="application_information_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
						<div v-if="$check_field('set','service_name') || $check_field('add','service_name') || $check_field('get','service_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								服务名称:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_service_name" v-model="form['service_name']" placeholder="请输入服务名称" v-if="(form['service_name'] && $check_field('set','service_name')) || (!form['service_name'] && $check_field('add','service_name'))"  :disabled="disabledObj['service_name_isDisabled']"/>
							<span v-else-if="$check_field('get','service_name')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','service_type') || $check_field('add','service_type') || $check_field('get','service_type')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								服务类型:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_service_type" v-model="form['service_type']" placeholder="请输入服务类型" v-if="(form['service_type'] && $check_field('set','service_type')) || (!form['service_type'] && $check_field('add','service_type'))"  :disabled="disabledObj['service_type_isDisabled']"/>
							<span v-else-if="$check_field('get','service_type')">{{ form['${obj.name}'] }}</span>
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
							<div v-if="$check_field('set','application_remarks') || $check_field('add','application_remarks') || $check_field('get','application_remarks')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								申请备注:
							</span>
						</div>
								<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_application_remarks" v-model="form['application_remarks']" v-if="(form['application_remarks'] && $check_field('set','application_remarks')) || (!form['application_remarks'] && $check_field('add','application_remarks'))" :disabled="disabledObj['application_remarks_isDisabled']" />
							<span v-else-if="$check_field('get','application_remarks')">{{ form['${obj.name}'] }}</span>
						</div>
							</div>
	


					<div v-if="user_group === '管理员' || $check_examine()" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								审核状态:
							</span>
						</div>
						<div class="diy_field diy_select" v-if="$check_action('/application_information/edit','examine')">
							<!--<span> {{ form['examine_state'] }} </span>-->
							<select v-model="form['examine_state']">
								<option value="未审核">
									未审核
								</option>
								<option value="已通过">
									已通过
								</option>
								<option value="未通过">
									未通过
								</option>
							</select>
						</div>
						<div class="diy_field diy_text" v-else>
							<span>
								{{ form['examine_state'] }}
							</span>
						</div>
					</div>
					<div v-if="user_group === '管理员' || $check_examine()" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								审核回复:
							</span>
						</div>
						<div class="diy_field diy_desc" v-if="$check_action('/application_information/edit','examine')">
							<textarea v-model="form['examine_reply']"></textarea>
						</div>
						<div class="diy_field diy_text" v-else>
							<span>
								{{ form['examine_reply'] }}
							</span>
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
				url_get_obj: "~/api/application_information/get_obj?",
				url_add: "~/api/application_information/add?",
				url_set: "~/api/application_information/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
						"service_name": "",
							"service_type": "",
							"resident_information": 0,
							"resident_name": "",
							"contact_number": "",
							"application_remarks": "",
						"application_information_id": 0,
				},

				obj: {
						"service_name":  '', // 服务名称
							"service_type":  '', // 服务类型
							"resident_information": 0, // 住户信息
							"resident_name":  '', // 住户姓名
							"contact_number":  '', // 联系电话
							"application_remarks":  '', // 申请备注
						"examine_state": "未审核",
					"examine_reply": "",
					"application_information_id": 0,
				},

				// 表单字段
				form: {
						"service_name":  '', // 服务名称
							"service_type":  '', // 服务类型
							"resident_information": 0, // 住户信息
							"resident_name":  '', // 住户姓名
							"contact_number":  '', // 联系电话
							"application_remarks":  '', // 申请备注
						"examine_state": "未审核",
					"examine_reply": "",
					"application_information_id": 0,
				},
				disabledObj:{
						"service_name_isDisabled": false,
							"service_type_isDisabled": false,
							"resident_information_isDisabled": false,
							"resident_name_isDisabled": false,
							"contact_number_isDisabled": false,
							"application_remarks_isDisabled": false,
					},

										// 用户列表
				list_user_resident_information: [],
							
				// ID字段
				field: "application_information_id",

			}
		},
		methods: {
			
				
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
	
				
				
				
	
			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/application_information/upload?", form, (res) => {
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
											this.get_user_session_resident_information();
					this.get_list_user_resident_information();
												},
	}
</script>

<style>




</style>
