<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','service_name') || $check_field('add','service_name') || $check_field('set','service_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="服务名称" prop="service_name">
												<el-input id="service_name" v-model="form['service_name']" placeholder="请输入服务名称"
							  v-if="user_group === '管理员' || (form['epidemic_service_id'] && $check_field('set','service_name')) || (!form['epidemic_service_id'] && $check_field('add','service_name'))" :disabled="disabledObj['service_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','service_name')">{{form['service_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','service_type') || $check_field('add','service_type') || $check_field('set','service_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="服务类型" prop="service_type">
								<el-select id="service_type" v-model="form['service_type']"
						v-if="user_group === '管理员' || (form['epidemic_service_id'] && $check_field('set','service_type')) || (!form['epidemic_service_id'] && $check_field('add','service_type'))">
						<el-option v-for="o in list_service_type" :key="o['service_type']" :label="o['service_type']"
							:value="o['service_type']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','service_type')">{{form['service_type']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','release_date') || $check_field('add','release_date') || $check_field('set','release_date')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="发布日期" prop="release_date">
								<el-date-picker :disabled="disabledObj['release_date_isDisabled']" v-if="user_group === '管理员' || (form['epidemic_service_id'] && $check_field('set','release_date')) || (!form['epidemic_service_id'] && $check_field('add','release_date'))" id="release_date"
						v-model="form['release_date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','release_date')">{{form['release_date']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','cover_photo') || $check_field('add','cover_photo') || $check_field('set','cover_photo')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="封面图片" prop="cover_photo">
								<el-upload :disabled="disabledObj['cover_photo_isDisabled']" id="cover_photo" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_cover_photo"
						:show-file-list="false" v-if="user_group === '管理员' || (form['epidemic_service_id'] && $check_field('set','cover_photo')) || (!form['epidemic_service_id'] && $check_field('add','cover_photo'))">
						<img v-if="form['cover_photo']" :src="$fullUrl(form['cover_photo'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','cover_photo')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['cover_photo'])" :preview-src-list="[$fullUrl(form['cover_photo'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','service_introduction') || $check_field('add','service_introduction') || $check_field('set','service_introduction')" :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="服务介绍" prop="service_introduction">
					<quill-editor v-model.number="form['service_introduction']"
						v-if="user_group === '管理员' || (form['epidemic_service_id'] && $check_field('set','service_introduction')) || (!form['epidemic_service_id'] && $check_field('add','service_introduction')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','service_introduction')" v-html="form['service_introduction']"></div>
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
				field: "epidemic_service_id",
				url_add: "~/api/epidemic_service/add?",
				url_set: "~/api/epidemic_service/set?",
				url_get_obj: "~/api/epidemic_service/get_obj?",
				url_upload: "~/api/epidemic_service/upload?",

				query: {
					"epidemic_service_id": 0,
				},

				form: {
								"service_name":  '', // 服务名称
										"service_type":  '', // 服务类型
										"release_date":  '', // 发布日期
										"cover_photo":  '', // 封面图片
										"service_introduction":  '', // 服务介绍
											"epidemic_service_id": 0, // ID
						
				},
				disabledObj:{
								"service_name_isDisabled": false,
										"service_type_isDisabled": false,
										"release_date_isDisabled": false,
										"cover_photo_isDisabled": false,
										"service_introduction_isDisabled": false,
										},

	
						// 服务类型选项列表
				list_service_type: [""],
	
		
		
		
	
			}
		},
		methods: {


	
	
			
				/**
			 * 获取服务类型列表
			 */
			async get_list_service_type() {
				var json = await this.$get("~/api/type_management/get_list?");
				if(json.result && json.result.list){
					this.list_service_type = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
	
			
	
						/**
			 * 上传封面图片
			 * @param {Object} param 图片参数
			 */
			upload_cover_photo(param){
						this.uploadFile(param.file, "cover_photo");
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
						        if (this.form["release_date"].indexOf("-")===-1){
          this.form["release_date"] = this.$toTime(parseInt(this.form["release_date"]),"yyyy-MM-dd")
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


								        if(this.form["release_date"]=="0000-00-00"){
          this.form["release_date"] = null;
        }
				if(parseInt(this.form["release_date"]) > 9999){
					this.form["release_date"] = this.$toTime(parseInt(this.form["release_date"]),"yyyy-MM-dd")
				}
								

			},


			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/epidemic_service/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/epidemic_service/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/epidemic_service/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/epidemic_service/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/epidemic_service/view','get');
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
						this.get_list_service_type();
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
