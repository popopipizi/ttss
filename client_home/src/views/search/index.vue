<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 商品搜索结果 -->
			  <list_result_search
				:list="result_goods"
				title="菜品信息"
				source_table="goods"
			  ></list_result_search>

				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="餐饮资讯"
				source_table="article"
			  ></list_result_search>


						  <list_result_search
				v-if="$check_action('/ordinary_users/list', 'get')"
				:list="result_ordinary_users_user_name"
				title="普通用户用户姓名"
				source_table="ordinary_users"
			  ></list_result_search>
															  <list_result_search
				v-if="$check_action('/seat_information/list', 'get')"
				:list="result_seat_information_seat_number"
				title="座位信息座位号码"
				source_table="seat_information"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/seat_information/list', 'get')"
				:list="result_seat_information_number_of_seats"
				title="座位信息座位人数"
				source_table="seat_information"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/seat_information/list', 'get')"
				:list="result_seat_information_seat_status"
				title="座位信息座位状态"
				source_table="seat_information"
			  ></list_result_search>
												  <list_result_search
				v-if="$check_action('/opening_table_information/list', 'get')"
				:list="result_opening_table_information_seat_number"
				title="开桌信息座位号码"
				source_table="opening_table_information"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/opening_table_information/list', 'get')"
				:list="result_opening_table_information_number_of_seats"
				title="开桌信息座位人数"
				source_table="opening_table_information"
			  ></list_result_search>
											  <list_result_search
				v-if="$check_action('/opening_table_information/list', 'get')"
				:list="result_opening_table_information_user_name"
				title="开桌信息用户姓名"
				source_table="opening_table_information"
			  ></list_result_search>
												  <list_result_search
				v-if="$check_action('/dish_information/list', 'get')"
				:list="result_dish_information_notice"
				title="菜品信息须知"
				source_table="dish_information"
			  ></list_result_search>
												  <list_result_search
				v-if="$check_action('/dish_orders/list', 'get')"
				:list="result_dish_orders_dish_name"
				title="菜品订单菜品名称"
				source_table="dish_orders"
			  ></list_result_search>
																	  <list_result_search
				v-if="$check_action('/dish_orders/list', 'get')"
				:list="result_dish_orders_user_name"
				title="菜品订单用户姓名"
				source_table="dish_orders"
			  ></list_result_search>
											  <list_result_search
				v-if="$check_action('/dish_orders/list', 'get')"
				:list="result_dish_orders_order_table_number"
				title="菜品订单订单桌号"
				source_table="dish_orders"
			  ></list_result_search>
																	  <list_result_search
				v-if="$check_action('/dish_orders/list', 'get')"
				:list="result_dish_orders_order_status"
				title="菜品订单订单状态"
				source_table="dish_orders"
			  ></list_result_search>
												  <list_result_search
				v-if="$check_action('/order_evaluation/list', 'get')"
				:list="result_order_evaluation_dish_name"
				title="订单评价菜品名称"
				source_table="order_evaluation"
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
	  "result_goods": [],
	  "result_article": [],
						"result_ordinary_users_user_name":[],
															"result_seat_information_seat_number":[],
								"result_seat_information_number_of_seats":[],
								"result_seat_information_seat_status":[],
												"result_opening_table_information_seat_number":[],
								"result_opening_table_information_number_of_seats":[],
											"result_opening_table_information_user_name":[],
												"result_dish_information_notice":[],
												"result_dish_orders_dish_name":[],
																	"result_dish_orders_user_name":[],
											"result_dish_orders_order_table_number":[],
																	"result_dish_orders_order_status":[],
												"result_order_evaluation_dish_name":[],
																};
  },
  methods: {
	/**
	 * 获取${fmodel.filter.cart_name}
	 */
	get_goods() {
	  this.$get("~/api/goods/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_goods = json.result.list;
		}
	  });
	},
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
	 * 获取user_name
	 */
	get_ordinary_users_user_name(){
		let url = "~/api/ordinary_users/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "user_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_ordinary_users_user_name = json.result.list;
			result_ordinary_users_user_name.map(o => o.title = o['user_name'])
	  			this.result_ordinary_users_user_name = result_ordinary_users_user_name
		 	}
		});
	},
													/**
	 * 获取seat_number
	 */
	get_seat_information_seat_number(){
		let url = "~/api/seat_information/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "seat_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_seat_information_seat_number = json.result.list;
			result_seat_information_seat_number.map(o => o.title = o['seat_number'])
	  			this.result_seat_information_seat_number = result_seat_information_seat_number
		 	}
		});
	},
						/**
	 * 获取number_of_seats
	 */
	get_seat_information_number_of_seats(){
		let url = "~/api/seat_information/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "number_of_seats": this.query.word }, (json) => {
		  if (json.result) {
			var result_seat_information_number_of_seats = json.result.list;
			result_seat_information_number_of_seats.map(o => o.title = o['number_of_seats'])
	  			this.result_seat_information_number_of_seats = result_seat_information_number_of_seats
		 	}
		});
	},
						/**
	 * 获取seat_status
	 */
	get_seat_information_seat_status(){
		let url = "~/api/seat_information/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "seat_status": this.query.word }, (json) => {
		  if (json.result) {
			var result_seat_information_seat_status = json.result.list;
			result_seat_information_seat_status.map(o => o.title = o['seat_status'])
	  			this.result_seat_information_seat_status = result_seat_information_seat_status
		 	}
		});
	},
										/**
	 * 获取seat_number
	 */
	get_opening_table_information_seat_number(){
		let url = "~/api/opening_table_information/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "seat_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_opening_table_information_seat_number = json.result.list;
			result_opening_table_information_seat_number.map(o => o.title = o['seat_number'])
	  			this.result_opening_table_information_seat_number = result_opening_table_information_seat_number
		 	}
		});
	},
						/**
	 * 获取number_of_seats
	 */
	get_opening_table_information_number_of_seats(){
		let url = "~/api/opening_table_information/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "number_of_seats": this.query.word }, (json) => {
		  if (json.result) {
			var result_opening_table_information_number_of_seats = json.result.list;
			result_opening_table_information_number_of_seats.map(o => o.title = o['number_of_seats'])
	  			this.result_opening_table_information_number_of_seats = result_opening_table_information_number_of_seats
		 	}
		});
	},
									/**
	 * 获取user_name
	 */
	get_opening_table_information_user_name(){
		let url = "~/api/opening_table_information/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "user_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_opening_table_information_user_name = json.result.list;
			result_opening_table_information_user_name.map(o => o.title = o['user_name'])
	  			this.result_opening_table_information_user_name = result_opening_table_information_user_name
		 	}
		});
	},
										/**
	 * 获取notice
	 */
	get_dish_information_notice(){
		let url = "~/api/dish_information/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "notice": this.query.word }, (json) => {
		  if (json.result) {
			var result_dish_information_notice = json.result.list;
			result_dish_information_notice.map(o => o.title = o['notice'])
	  			this.result_dish_information_notice = result_dish_information_notice
		 	}
		});
	},
										/**
	 * 获取dish_name
	 */
	get_dish_orders_dish_name(){
		let url = "~/api/dish_orders/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "dish_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_dish_orders_dish_name = json.result.list;
			result_dish_orders_dish_name.map(o => o.title = o['dish_name'])
	  			this.result_dish_orders_dish_name = result_dish_orders_dish_name
		 	}
		});
	},
															/**
	 * 获取user_name
	 */
	get_dish_orders_user_name(){
		let url = "~/api/dish_orders/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "user_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_dish_orders_user_name = json.result.list;
			result_dish_orders_user_name.map(o => o.title = o['user_name'])
	  			this.result_dish_orders_user_name = result_dish_orders_user_name
		 	}
		});
	},
									/**
	 * 获取order_table_number
	 */
	get_dish_orders_order_table_number(){
		let url = "~/api/dish_orders/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "order_table_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_dish_orders_order_table_number = json.result.list;
			result_dish_orders_order_table_number.map(o => o.title = o['order_table_number'])
	  			this.result_dish_orders_order_table_number = result_dish_orders_order_table_number
		 	}
		});
	},
															/**
	 * 获取order_status
	 */
	get_dish_orders_order_status(){
		let url = "~/api/dish_orders/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "order_status": this.query.word }, (json) => {
		  if (json.result) {
			var result_dish_orders_order_status = json.result.list;
			result_dish_orders_order_status.map(o => o.title = o['order_status'])
	  			this.result_dish_orders_order_status = result_dish_orders_order_status
		 	}
		});
	},
										/**
	 * 获取dish_name
	 */
	get_order_evaluation_dish_name(){
		let url = "~/api/order_evaluation/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "dish_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_order_evaluation_dish_name = json.result.list;
			result_order_evaluation_dish_name.map(o => o.title = o['dish_name'])
	  			this.result_order_evaluation_dish_name = result_order_evaluation_dish_name
		 	}
		});
	},
															
  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_goods();
	this.get_article();
					this.get_ordinary_users_user_name();
														this.get_seat_information_seat_number();
							this.get_seat_information_number_of_seats();
							this.get_seat_information_seat_status();
											this.get_opening_table_information_seat_number();
							this.get_opening_table_information_number_of_seats();
										this.get_opening_table_information_user_name();
											this.get_dish_information_notice();
											this.get_dish_orders_dish_name();
																this.get_dish_orders_user_name();
										this.get_dish_orders_order_table_number();
																this.get_dish_orders_order_status();
											this.get_order_evaluation_dish_name();
															  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_goods();
	  this.get_article();
				  this.get_ordinary_users_user_name();
													  this.get_seat_information_seat_number();
						  this.get_seat_information_number_of_seats();
						  this.get_seat_information_seat_status();
										  this.get_opening_table_information_seat_number();
						  this.get_opening_table_information_number_of_seats();
									  this.get_opening_table_information_user_name();
										  this.get_dish_information_notice();
										  this.get_dish_orders_dish_name();
															  this.get_dish_orders_user_name();
									  this.get_dish_orders_order_table_number();
															  this.get_dish_orders_order_status();
										  this.get_order_evaluation_dish_name();
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
