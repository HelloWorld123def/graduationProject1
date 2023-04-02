<template>
	<div class="diy_list page_epidemic_service" id="epidemic_service_list">
		<div class="warp">
			<div class="container diy_list_container">
				<div class="diy_list_title">
					<div class="col">
						<span class="title">疫情服务列表</span>
					</div>
				</div>
				<div class="row diy_list_search">
					<div class="col">
						<!-- 搜索栏 -->
						<div class="view">
							<span class="diy_list_search_title">关键字搜索：</span>
								<b-form-input size="sm" class="mr-sm-2" placeholder="服务名称搜索" v-model="query['service_name']" />
									<b-form-input size="sm" class="mr-sm-2" placeholder="服务类型搜索" v-model="query['service_type']" />
											<b-button size="sm" @click="search()" >
								<b-icon icon="search"/>
							</b-button>
						</div>
						<!-- /搜索栏 -->
					</div>
				</div>
				<div class="diy_list_select_box">
					<span class="diy_list_select_title">下拉搜索：</span>
						<div class="diy_list_dropdown_box">
						<div class="col">
							<!-- 筛选 -->
							<div class="view">
										<div class="bar_filter">
				<div class="bar_filter" style="font-weight: 700;
    color: #fff;
    font-size: 22px;">服务类型</div>
				<button type="button" class="btn btn-secondary"
						@click="filter_set('全部','service_type')">全部</button>
				<button type="button" class="btn btn-secondary" v-for="(o, i) in list_service_type"
						:key="i"
						@click="filter_set(o['service_type'],'service_type')">{{ o['service_type'] }}</button>
			</div>
													</div>
							<!-- /筛选 -->
						</div>
					</div>
					<div class="diy_list_sort_box">
						<div class="col">
							<!-- 排序 -->
							<div class="view">
								<div class="bar_filter" style="font-weight: 700;
    color: #fff;
    font-size: 22px;">排序</div>
                                <!-- 排序 -->
                                <div
                                        class="bar_sort"
                                        :class="{ active: query.orderby == '`hits` desc' }"
                                >
                                    <button type="button" class="btn btn-secondary" v-for="(o, i) in list_sort"
                                    :key="i" @click="set_sort(o)">{{ o.name }}</button>
                                </div>
							</div>
							<!--/排序 -->
						</div>
					</div>
				</div>

				<div class="row diy_list_box">
					<div class="col">
						<!-- 列表 -->
						<list_epidemic_service :list="list" />
						<!-- /列表 -->
					</div>
				</div>
				<div class="row diy_list_page_box">
					<div class="col overflow-auto flex_cc">
						<!-- 分页器 -->
<!--						<diy_pager v-model="query['page']" :size="query['size']" :count="count" v-on:toPage="toPage" v-on:toSize="toSize" ></diy_pager>-->
            <b-pagination
                v-model="query.page"
                :total-rows="count"
                :per-page="query.size"
                @change="goToPage"
            />
						<!-- /分页器 -->
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import list_epidemic_service from "@/components/diy/list_epidemic_service.vue";
	import diy_pager from "@/components/diy/diy_pager";
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		components: {
			diy_pager,
			list_epidemic_service
		},
		data() {
			return {
				url_get_list: "~/api/epidemic_service/get_list?like=0",

				// 查询条件
				query: {
					keyword: "",
					page: 1,
					size: 10,
								"service_name": "", // 服务名称
											"service_type": "", // 服务类型
										},

				// 排序内容
				list_sort: [{
						name: "创建时间从高到低",
						value: "create_time desc",
					},
					{
						name: "创建时间从低到高",
						value: "create_time asc",
					},
					{
						name: "更新时间从高到低",
						value: "update_time desc",
					},
					{
						name: "更新时间从低到高",
						value: "update_time asc",
					},
						{
						name: "服务名称正序",
						value: "service_name asc",
					},
					{
						name: "服务名称倒序",
						value: "service_name desc",
					},
							{
						name: "服务类型正序",
						value: "service_type asc",
					},
					{
						name: "服务类型倒序",
						value: "service_type desc",
					},
								],

							// 服务类型列表
				"list_service_type": [""],
								
			}
		},
		methods: {
      get_list_before(param) {
      },
			/**
			 * 筛选选择
			 */
			filter_set(o,key) {
			    if (o == "全部") {
			        this.query[key] = "";
			    } else {
			        this.query[key] = o;
			    }
			    this.search();
			},

			/**
			 * 排序
			 */
			set_sort(o) {
			    this.query.orderby = o.value;
			    this.search();
			},


					/**
			 * 获取服务类型列表
			 */
			async get_list_service_type() {
				var json = await this.$get("~/api/type_management/get_list?");
				if (json.result) {
					this.list_service_type = json.result.list;
				} else if (json.error) {
					console.log(json.error);
				}
			},
				
			/**
			 * 筛选
			 */
												filter_service_type(o) {
				if (o == "全部") {
					this.query["service_type"] = "";
				} else {
					this.query["service_type"] = o;
				}
				this.search();
			},
											/**
			 * 重置
			 */
			reset() {
							this.query.service_name = ""
										this.query.service_type = ""
										this.search();
			},

			// 返回条数
			toSize(i){
				this.query.size = i;
				this.first();
			},

			// 返回页数
			toPage(i){
				this.query.page = i;
				this.first();
			},

      goToPage(v){
        this.query.page = v;
        this.goToNew(v)
      },

		},
		computed: {
		},
		created() {
						/**
			 * 获取服务类型列表
			 */
			this.get_list_service_type();
										}
	}
</script>

<style>
</style>
