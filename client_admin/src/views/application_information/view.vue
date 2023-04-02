<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','service_name') || $check_field('add','service_name') || $check_field('set','service_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="服务名称" prop="service_name">
												<el-input id="service_name" v-model="form['service_name']" placeholder="请输入服务名称"
							  v-if="user_group === '管理员' || (form['application_information_id'] && $check_field('set','service_name')) || (!form['application_information_id'] && $check_field('add','service_name'))" :disabled="disabledObj['service_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','service_name')">{{form['service_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','service_type') || $check_field('add','service_type') || $check_field('set','service_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="服务类型" prop="service_type">
												<el-input id="service_type" v-model="form['service_type']" placeholder="请输入服务类型"
							  v-if="user_group === '管理员' || (form['application_information_id'] && $check_field('set','service_type')) || (!form['application_information_id'] && $check_field('add','service_type'))" :disabled="disabledObj['service_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','service_type')">{{form['service_type']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','resident_information') || $check_field('add','resident_information') || $check_field('set','resident_information')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="住户信息" prop="resident_information">
																		<div v-if="user_group !== '管理员'">
							{{ get_user_session_resident_information(form['resident_information']) }}
							<!--<el-input id="business_name" v-model="form['resident_information']" placeholder="请输入住户信息"-->
							<!--v-if="user_group === '管理员' || (form['application_information_id'] && $check_field('set','resident_information')) || (!form['application_information_id'] && $check_field('add','resident_information'))" :disabled="disabledObj['resident_information_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','resident_information')">{{form['resident_information']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['application_information_id'] && $check_field('set','resident_information')) || (!form['application_information_id'] && $check_field('add','resident_information'))" id="resident_information" v-model="form['resident_information']" :disabled="disabledObj['resident_information_isDisabled']">
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
							  v-if="user_group === '管理员' || (form['application_information_id'] && $check_field('set','resident_name')) || (!form['application_information_id'] && $check_field('add','resident_name'))" :disabled="disabledObj['resident_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','resident_name')">{{form['resident_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','contact_number') || $check_field('add','contact_number') || $check_field('set','contact_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="联系电话" prop="contact_number">
												<el-input id="contact_number" v-model="form['contact_number']" placeholder="请输入联系电话"
							  v-if="user_group === '管理员' || (form['application_information_id'] && $check_field('set','contact_number')) || (!form['application_information_id'] && $check_field('add','contact_number'))" :disabled="disabledObj['contact_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','contact_number')">{{form['contact_number']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','application_remarks') || $check_field('add','application_remarks') || $check_field('set','application_remarks')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="申请备注" prop="application_remarks">
								<el-input type="textarea" id="application_remarks" v-model="form['application_remarks']" placeholder="请输入申请备注"
						v-if="user_group === '管理员' || (form['application_information_id'] && $check_field('set','application_remarks')) || (!form['application_information_id'] && $check_field('add','application_remarks'))" :disabled="disabledObj['application_remarks_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','application_remarks')">{{form['application_remarks']}}</div>
							</el-form-item>
			</el-col>
							<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="审核状态" prop="examine_state">
					<el-select id="examine_state" v-model="form['examine_state']"
						v-if="user_group === '管理员' || (form['examine_state'] && $check_examine()) || (!form['examine_state'] && $check_examine())">
						<el-option key="未审核" label="未审核" value="未审核"></el-option>
						<el-option key="已通过" label="已通过" value="已通过"></el-option>
						<el-option key="未通过" label="未通过" value="未通过"></el-option>
					</el-select>
					<div v-else>{{form["examine_state"]}}</div>
				</el-form-item>
			</el-col>
					<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="审核回复" prop="examine_reply">
					<el-input id="examine_reply" v-model="form['examine_reply']" placeholder="请输入审核回复"
						v-if="user_group === '管理员' || (form['examine_reply'] && $check_examine()) || (!form['examine_reply'] && $check_examine())"></el-input>
					<div v-else>{{form["examine_reply"]}}</div>
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
				field: "application_information_id",
				url_add: "~/api/application_information/add?",
				url_set: "~/api/application_information/set?",
				url_get_obj: "~/api/application_information/get_obj?",
				url_upload: "~/api/application_information/upload?",

				query: {
					"application_information_id": 0,
				},

				form: {
								"service_name":  '', // 服务名称
										"service_type":  '', // 服务类型
										"resident_information": 0, // 住户信息
										"resident_name":  '', // 住户姓名
										"contact_number":  '', // 联系电话
										"application_remarks":  '', // 申请备注
									"examine_state": "未审核",
							"examine_reply": "",
							"application_information_id": 0, // ID
						
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
						// 用户组
				group_user_resident_information: "",
				
		
		
	
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
					bl = this.$check_action('/application_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/application_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/application_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/application_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/application_information/view','get');
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
