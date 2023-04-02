<template>
	<div>
			<el-form-item v-if="$check_register_field('add','merchant_name','/merchant_user/view')" label="商户姓名" prop="merchant_name">
				<el-input id="merchant_name" v-model="form['merchant_name']" placeholder="请输入商户姓名"></el-input>
				</el-form-item>
				<el-form-item v-if="$check_register_field('add','merchant_gender','/merchant_user/view')" label="商户性别" prop="merchant_gender">
				<el-select id="merchant_gender" v-model="form['merchant_gender']">
			<el-option v-for="o in list_merchant_gender" :key="o" :label="o" :value="o">
			</el-option>
		</el-select>
				</el-form-item>
				<el-form-item v-if="$check_register_field('add','store_name','/merchant_user/view')" label="店铺名称" prop="store_name">
				<el-input id="store_name" v-model="form['store_name']" placeholder="请输入店铺名称"></el-input>
				</el-form-item>
	
	</div>
</template>

<script>
	import mixin from "@/mixins/component.js";

	export default {
		mixins: [mixin],
		props:{

			form:{
				type: Object,
				default: function(){
					return {
							"merchant_name":  '' ,
								"merchant_gender":  '' ,
								"store_name":  '' ,
						}
				}
			},

		},
		data() {
			return {
				field: "merchant_user_id",
				url_add: "~/api/merchant_user/add?",
				url_set: "~/api/merchant_user/set?",
				url_upload: "~/api/merchant_user/upload?",

						list_merchant_gender: ['男','女'],
		
				rules: {
					"merchant_name": [    {required: true,message: '商户姓名不能为空'},  ],
					"merchant_gender": [    {required: true,message: '商户性别不能为空'},  ],
					"store_name": [    {required: true,message: '店铺名称不能为空'},  ],
				}

			}
		},
		methods: {

	
	
	

	
	
	

	
	
	

		},
		created() {
					}
	}
</script>
