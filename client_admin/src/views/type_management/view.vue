<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','service_type') || $check_field('add','service_type') || $check_field('set','service_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="服务类型" prop="service_type">
												<el-input id="service_type" v-model="form['service_type']" placeholder="请输入服务类型"
							  v-if="user_group === '管理员' || (form['type_management_id'] && $check_field('set','service_type')) || (!form['type_management_id'] && $check_field('add','service_type'))" :disabled="disabledObj['service_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','service_type')">{{form['service_type']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','type_description') || $check_field('add','type_description') || $check_field('set','type_description')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="类型描述" prop="type_description">
								<el-input type="textarea" id="type_description" v-model="form['type_description']" placeholder="请输入类型描述"
						v-if="user_group === '管理员' || (form['type_management_id'] && $check_field('set','type_description')) || (!form['type_management_id'] && $check_field('add','type_description'))" :disabled="disabledObj['type_description_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','type_description')">{{form['type_description']}}</div>
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
				field: "type_management_id",
				url_add: "~/api/type_management/add?",
				url_set: "~/api/type_management/set?",
				url_get_obj: "~/api/type_management/get_obj?",
				url_upload: "~/api/type_management/upload?",

				query: {
					"type_management_id": 0,
				},

				form: {
								"service_type":  '', // 服务类型
										"type_description":  '', // 类型描述
											"type_management_id": 0, // ID
						
				},
				disabledObj:{
								"service_type_isDisabled": false,
										"type_description_isDisabled": false,
										},

	
		
	
			}
		},
		methods: {


	
	
			
	
		
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
					bl = this.$check_action('/type_management/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/type_management/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/type_management/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/type_management/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/type_management/view','get');
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
