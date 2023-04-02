<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>

				<!--{if filter.article}-->
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="疫情资讯"
				source_table="article"
			  ></list_result_search>
				<!--{/if}-->

				<!-- 论坛搜索结果 -->
			  <list_result_search
				:list="result_forum"
				title="交流论坛"
				source_table="forum"
			  ></list_result_search>

						  <list_result_search
				v-if="$check_action('/household_users/list', 'get')"
				:list="result_household_users_resident_name"
				title="住户用户住户姓名"
				source_table="household_users"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/household_users/list', 'get')"
				:list="result_household_users_household_gender"
				title="住户用户住户性别"
				source_table="household_users"
			  ></list_result_search>
									  <list_result_search
				v-if="$check_action('/merchant_user/list', 'get')"
				:list="result_merchant_user_merchant_name"
				title="商户用户商户姓名"
				source_table="merchant_user"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/merchant_user/list', 'get')"
				:list="result_merchant_user_merchant_gender"
				title="商户用户商户性别"
				source_table="merchant_user"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/merchant_user/list', 'get')"
				:list="result_merchant_user_store_name"
				title="商户用户店铺名称"
				source_table="merchant_user"
			  ></list_result_search>
									  <list_result_search
				v-if="$check_action('/product_information/list', 'get')"
				:list="result_product_information_store_name"
				title="商品信息店铺名称"
				source_table="product_information"
			  ></list_result_search>
											  <list_result_search
				v-if="$check_action('/product_information/list', 'get')"
				:list="result_product_information_trade_name"
				title="商品信息商品名称"
				source_table="product_information"
			  ></list_result_search>
																								  <list_result_search
				v-if="$check_action('/purchase_information/list', 'get')"
				:list="result_purchase_information_trade_name"
				title="购买信息商品名称"
				source_table="purchase_information"
			  ></list_result_search>
																																  <list_result_search
				v-if="$check_action('/purchase_information/list', 'get')"
				:list="result_purchase_information_order_status"
				title="购买信息订单状态"
				source_table="purchase_information"
			  ></list_result_search>
												  <list_result_search
				v-if="$check_action('/epidemic_service/list', 'get')"
				:list="result_epidemic_service_service_name"
				title="疫情服务服务名称"
				source_table="epidemic_service"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/epidemic_service/list', 'get')"
				:list="result_epidemic_service_service_type"
				title="疫情服务服务类型"
				source_table="epidemic_service"
			  ></list_result_search>
																		  <list_result_search
				v-if="$check_action('/type_management/list', 'get')"
				:list="result_type_management_service_type"
				title="类型管理服务类型"
				source_table="type_management"
			  ></list_result_search>
												  <list_result_search
				v-if="$check_action('/application_information/list', 'get')"
				:list="result_application_information_service_name"
				title="申请信息服务名称"
				source_table="application_information"
			  ></list_result_search>
																					</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
	  "result_forum": [],
						"result_household_users_resident_name":[],
								"result_household_users_household_gender":[],
									"result_merchant_user_merchant_name":[],
								"result_merchant_user_merchant_gender":[],
								"result_merchant_user_store_name":[],
									"result_product_information_store_name":[],
											"result_product_information_trade_name":[],
																								"result_purchase_information_trade_name":[],
																																"result_purchase_information_order_status":[],
												"result_epidemic_service_service_name":[],
								"result_epidemic_service_service_type":[],
																		"result_type_management_service_type":[],
												"result_application_information_service_name":[],
																			};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取交流论坛
	 */
	get_forum() {
	  this.$get("~/api/forum/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_forum = json.result.list;
		}
	  });
	},

				/**
	 * 获取resident_name
	 */
	get_household_users_resident_name(){
		this.$get("~/api/household_users/get_list?like=0", { page: 1, size: 10, "resident_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_household_users_resident_name = json.result.list;
			result_household_users_resident_name.map(o => o.title = o['resident_name'])
	  			this.result_household_users_resident_name = result_household_users_resident_name
		 	}
		});
	},
						/**
	 * 获取household_gender
	 */
	get_household_users_household_gender(){
		this.$get("~/api/household_users/get_list?like=0", { page: 1, size: 10, "household_gender": this.query.word }, (json) => {
		  if (json.result) {
			var result_household_users_household_gender = json.result.list;
			result_household_users_household_gender.map(o => o.title = o['household_gender'])
	  			this.result_household_users_household_gender = result_household_users_household_gender
		 	}
		});
	},
							/**
	 * 获取merchant_name
	 */
	get_merchant_user_merchant_name(){
		this.$get("~/api/merchant_user/get_list?like=0", { page: 1, size: 10, "merchant_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_merchant_user_merchant_name = json.result.list;
			result_merchant_user_merchant_name.map(o => o.title = o['merchant_name'])
	  			this.result_merchant_user_merchant_name = result_merchant_user_merchant_name
		 	}
		});
	},
						/**
	 * 获取merchant_gender
	 */
	get_merchant_user_merchant_gender(){
		this.$get("~/api/merchant_user/get_list?like=0", { page: 1, size: 10, "merchant_gender": this.query.word }, (json) => {
		  if (json.result) {
			var result_merchant_user_merchant_gender = json.result.list;
			result_merchant_user_merchant_gender.map(o => o.title = o['merchant_gender'])
	  			this.result_merchant_user_merchant_gender = result_merchant_user_merchant_gender
		 	}
		});
	},
						/**
	 * 获取store_name
	 */
	get_merchant_user_store_name(){
		this.$get("~/api/merchant_user/get_list?like=0", { page: 1, size: 10, "store_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_merchant_user_store_name = json.result.list;
			result_merchant_user_store_name.map(o => o.title = o['store_name'])
	  			this.result_merchant_user_store_name = result_merchant_user_store_name
		 	}
		});
	},
							/**
	 * 获取store_name
	 */
	get_product_information_store_name(){
		this.$get("~/api/product_information/get_list?like=0", { page: 1, size: 10, "store_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_product_information_store_name = json.result.list;
			result_product_information_store_name.map(o => o.title = o['store_name'])
	  			this.result_product_information_store_name = result_product_information_store_name
		 	}
		});
	},
									/**
	 * 获取trade_name
	 */
	get_product_information_trade_name(){
		this.$get("~/api/product_information/get_list?like=0", { page: 1, size: 10, "trade_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_product_information_trade_name = json.result.list;
			result_product_information_trade_name.map(o => o.title = o['trade_name'])
	  			this.result_product_information_trade_name = result_product_information_trade_name
		 	}
		});
	},
																						/**
	 * 获取trade_name
	 */
	get_purchase_information_trade_name(){
		this.$get("~/api/purchase_information/get_list?like=0", { page: 1, size: 10, "trade_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_purchase_information_trade_name = json.result.list;
			result_purchase_information_trade_name.map(o => o.title = o['trade_name'])
	  			this.result_purchase_information_trade_name = result_purchase_information_trade_name
		 	}
		});
	},
																														/**
	 * 获取order_status
	 */
	get_purchase_information_order_status(){
		this.$get("~/api/purchase_information/get_list?like=0", { page: 1, size: 10, "order_status": this.query.word }, (json) => {
		  if (json.result) {
			var result_purchase_information_order_status = json.result.list;
			result_purchase_information_order_status.map(o => o.title = o['order_status'])
	  			this.result_purchase_information_order_status = result_purchase_information_order_status
		 	}
		});
	},
										/**
	 * 获取service_name
	 */
	get_epidemic_service_service_name(){
		this.$get("~/api/epidemic_service/get_list?like=0", { page: 1, size: 10, "service_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_epidemic_service_service_name = json.result.list;
			result_epidemic_service_service_name.map(o => o.title = o['service_name'])
	  			this.result_epidemic_service_service_name = result_epidemic_service_service_name
		 	}
		});
	},
						/**
	 * 获取service_type
	 */
	get_epidemic_service_service_type(){
		this.$get("~/api/epidemic_service/get_list?like=0", { page: 1, size: 10, "service_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_epidemic_service_service_type = json.result.list;
			result_epidemic_service_service_type.map(o => o.title = o['service_type'])
	  			this.result_epidemic_service_service_type = result_epidemic_service_service_type
		 	}
		});
	},
																/**
	 * 获取service_type
	 */
	get_type_management_service_type(){
		this.$get("~/api/type_management/get_list?like=0", { page: 1, size: 10, "service_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_type_management_service_type = json.result.list;
			result_type_management_service_type.map(o => o.title = o['service_type'])
	  			this.result_type_management_service_type = result_type_management_service_type
		 	}
		});
	},
										/**
	 * 获取service_name
	 */
	get_application_information_service_name(){
		this.$get("~/api/application_information/get_list?like=0", { page: 1, size: 10, "service_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_application_information_service_name = json.result.list;
			result_application_information_service_name.map(o => o.title = o['service_name'])
	  			this.result_application_information_service_name = result_application_information_service_name
		 	}
		});
	},
																		
  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
	this.get_forum();
					this.get_household_users_resident_name();
							this.get_household_users_household_gender();
								this.get_merchant_user_merchant_name();
							this.get_merchant_user_merchant_gender();
							this.get_merchant_user_store_name();
								this.get_product_information_store_name();
										this.get_product_information_trade_name();
																							this.get_purchase_information_trade_name();
																															this.get_purchase_information_order_status();
											this.get_epidemic_service_service_name();
							this.get_epidemic_service_service_type();
																	this.get_type_management_service_type();
											this.get_application_information_service_name();
																		  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_forum();
				  this.get_household_users_resident_name();
						  this.get_household_users_household_gender();
							  this.get_merchant_user_merchant_name();
						  this.get_merchant_user_merchant_gender();
						  this.get_merchant_user_store_name();
							  this.get_product_information_store_name();
									  this.get_product_information_trade_name();
																						  this.get_purchase_information_trade_name();
																														  this.get_purchase_information_order_status();
										  this.get_epidemic_service_service_name();
						  this.get_epidemic_service_service_type();
																  this.get_type_management_service_type();
										  this.get_application_information_service_name();
																			},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
