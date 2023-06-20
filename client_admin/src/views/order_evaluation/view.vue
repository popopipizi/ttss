<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','order_number') || $check_field('add','order_number') || $check_field('set','order_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="订单编号" prop="order_number">
												<el-input id="order_number" v-model="form['order_number']" placeholder="请输入订单编号"
							  v-if="user_group === '管理员' || (form['order_evaluation_id'] && $check_field('set','order_number')) || (!form['order_evaluation_id'] && $check_field('add','order_number'))" :disabled="disabledObj['order_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','order_number')">{{form['order_number']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','dish_name') || $check_field('add','dish_name') || $check_field('set','dish_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="菜品名称" prop="dish_name">
												<el-input id="dish_name" v-model="form['dish_name']" placeholder="请输入菜品名称"
							  v-if="user_group === '管理员' || (form['order_evaluation_id'] && $check_field('set','dish_name')) || (!form['order_evaluation_id'] && $check_field('add','dish_name'))" :disabled="disabledObj['dish_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','dish_name')">{{form['dish_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','price_of_dishes') || $check_field('add','price_of_dishes') || $check_field('set','price_of_dishes')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="菜品价格" prop="price_of_dishes">
												<el-input id="price_of_dishes" v-model="form['price_of_dishes']" placeholder="请输入菜品价格"
							  v-if="user_group === '管理员' || (form['order_evaluation_id'] && $check_field('set','price_of_dishes')) || (!form['order_evaluation_id'] && $check_field('add','price_of_dishes'))" :disabled="disabledObj['price_of_dishes_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','price_of_dishes')">{{form['price_of_dishes']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','order_user') || $check_field('add','order_user') || $check_field('set','order_user')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="订单用户" prop="order_user">
													<el-select v-if="user_group === '管理员' || (form['order_evaluation_id'] && $check_field('set','order_user')) || (!form['order_evaluation_id'] && $check_field('add','order_user'))" id="order_user" v-model="form['order_user']" :disabled="disabledObj['order_user_isDisabled']">
							<el-option v-for="o in list_user_order_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','order_user')" id="order_user" v-model="form['order_user']" :disabled="true">
							<el-option v-for="o in list_user_order_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','evaluation_level') || $check_field('add','evaluation_level') || $check_field('set','evaluation_level')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="评价等级" prop="evaluation_level">
								<el-select id="evaluation_level" v-model="form['evaluation_level']"
						v-if="user_group === '管理员' || (form['order_evaluation_id'] && $check_field('set','evaluation_level')) || (!form['order_evaluation_id'] && $check_field('add','evaluation_level'))">
						<el-option v-for="o in list_evaluation_level" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','evaluation_level')">{{form['evaluation_level']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','evaluation_content') || $check_field('add','evaluation_content') || $check_field('set','evaluation_content')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="评价内容" prop="evaluation_content">
								<el-input type="textarea" id="evaluation_content" v-model="form['evaluation_content']" placeholder="请输入评价内容"
						v-if="user_group === '管理员' || (form['order_evaluation_id'] && $check_field('set','evaluation_content')) || (!form['order_evaluation_id'] && $check_field('add','evaluation_content'))" :disabled="disabledObj['evaluation_content_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','evaluation_content')">{{form['evaluation_content']}}</div>
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
				field: "order_evaluation_id",
				url_add: "~/api/order_evaluation/add?",
				url_set: "~/api/order_evaluation/set?",
				url_get_obj: "~/api/order_evaluation/get_obj?",
				url_upload: "~/api/order_evaluation/upload?",

				query: {
					"order_evaluation_id": 0,
				},

				form: {
								"order_number":  '', // 订单编号
										"dish_name":  '', // 菜品名称
										"price_of_dishes":  '', // 菜品价格
										"order_user": 0, // 订单用户
										"evaluation_level":  '', // 评价等级
										"evaluation_content":  '', // 评价内容
											"order_evaluation_id": 0, // ID
						
				},
				disabledObj:{
								"order_number_isDisabled": false,
										"dish_name_isDisabled": false,
										"price_of_dishes_isDisabled": false,
										"order_user_isDisabled": false,
										"evaluation_level_isDisabled": false,
										"evaluation_content_isDisabled": false,
										},

	
		
		
		
					// 用户列表
				list_user_order_user: [],
								// 评价等级选项列表
				list_evaluation_level: ['5','4','3','2','1'],
	
		
	
			}
		},
		methods: {


	
	
			
	
			
	
			
	
				/**
			 * 获取普通用户用户列表
			 */
			async get_list_user_order_user() {
                // if(this.user_group !== "管理员" && this.form["order_user"] === 0) {
                //     this.form["order_user"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=普通用户");
                if(json.result && json.result.list){
                    this.list_user_order_user = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					get_user_order_user(id){
				var obj = this.list_user_order_user.getObj({"user_id":id});
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

			/**
			 * 提交前验证事件
			 * @param {Object} 请求参数
			 * @return {String} 验证成功返回null, 失败返回错误提示
			 */
			submit_check(param) {
				let msg = null
																								return msg;
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/order_evaluation/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/order_evaluation/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/order_evaluation/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/order_evaluation/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/order_evaluation/view','get');
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
											this.get_list_user_order_user();
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
