<template>
	<div class="diy_list page_seat_information" id="seat_information_list">
		<div class="warp">
			<div class="container diy_list_container">
				<div class="diy_list_title">
					<div class="col">
						<span class="title">座位信息列表</span>
					</div>
				</div>
				<div class="row diy_list_search">
					<div class="col">
						<!-- 搜索栏 -->
						<div class="view">
							<span class="diy_list_search_title">关键字搜索：</span>
								<b-form-input size="sm" class="mr-sm-2" placeholder="座位号码搜索" v-model="query['seat_number']" />
									<b-form-input size="sm" class="mr-sm-2" placeholder="座位人数搜索" v-model="query['number_of_seats']" />
									<b-form-input size="sm" class="mr-sm-2" placeholder="座位状态搜索" v-model="query['seat_status']" />
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
																			<b-dropdown text="座位状态" variant="outline-dark" left>
									<b-dropdown-item @click="filter_set('全部','seat_status')">全部</b-dropdown-item>
										<b-dropdown-item v-for="(o,i) in list_seat_status" @click="filter_set(o,'seat_status')" >
										{{ o }}
										</b-dropdown-item>
								</b-dropdown>
											</div>
							<!-- /筛选 -->
						</div>
					</div>
					<div class="diy_list_sort_box">
						<div class="col">
							<!-- 排序 -->
							<div class="view">
								<b-dropdown text="排序" variant="outline-dark" left>
										<b-dropdown-item v-for="(o, i) in list_sort" :key="i" @click="set_sort(o)" >
												{{ o.name }}
										</b-dropdown-item>
								</b-dropdown>
							</div>
							<!--/排序 -->
						</div>
					</div>
				</div>

				<div class="row diy_list_box">
					<div class="col">
						<!-- 列表 -->
						<list_seat_information :list="list" />
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
	import list_seat_information from "@/components/diy/list_seat_information.vue";
	import diy_pager from "@/components/diy/diy_pager";
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		components: {
			diy_pager,
			list_seat_information
		},
		data() {
			return {
				url_get_list: "~/api/seat_information/get_list?like=0",

				// 查询条件
				query: {
					keyword: "",
					page: 1,
					size: 10,
								"seat_number": "", // 座位号码
											"number_of_seats": "", // 座位人数
											"seat_status": "", // 座位状态
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
						name: "座位号码正序",
						value: "seat_number asc",
					},
					{
						name: "座位号码倒序",
						value: "seat_number desc",
					},
							{
						name: "座位人数正序",
						value: "number_of_seats asc",
					},
					{
						name: "座位人数倒序",
						value: "number_of_seats desc",
					},
							{
						name: "座位状态正序",
						value: "seat_status asc",
					},
					{
						name: "座位状态倒序",
						value: "seat_status desc",
					},
						],

									// 座位状态列表
				"list_seat_status": ['未开桌','已开桌'],
				
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
			 * 筛选
			 */
																		filter_seat_status(o) {
				if (o == "全部") {
					this.query["seat_status"] = "";
				} else {
					this.query["seat_status"] = o;
				}
				this.search();
			},
									/**
			 * 重置
			 */
			reset() {
							this.query.seat_number = ""
										this.query.number_of_seats = ""
										this.query.seat_status = ""
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
										}
	}
</script>

<style>
</style>
