<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','seat_number') || $check_field('add','seat_number') || $check_field('set','seat_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="座位号码" prop="seat_number">
												<el-input id="seat_number" v-model="form['seat_number']" placeholder="请输入座位号码"
							  v-if="user_group === '管理员' || (form['opening_table_information_id'] && $check_field('set','seat_number')) || (!form['opening_table_information_id'] && $check_field('add','seat_number'))" :disabled="disabledObj['seat_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','seat_number')">{{form['seat_number']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','number_of_seats') || $check_field('add','number_of_seats') || $check_field('set','number_of_seats')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="座位人数" prop="number_of_seats">
												<el-input id="number_of_seats" v-model="form['number_of_seats']" placeholder="请输入座位人数"
							  v-if="user_group === '管理员' || (form['opening_table_information_id'] && $check_field('set','number_of_seats')) || (!form['opening_table_information_id'] && $check_field('add','number_of_seats'))" :disabled="disabledObj['number_of_seats_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','number_of_seats')">{{form['number_of_seats']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','open_table_users') || $check_field('add','open_table_users') || $check_field('set','open_table_users')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="开桌用户" prop="open_table_users">
																		<div v-if="user_group !== '管理员'">
							{{ get_user_session_open_table_users(form['open_table_users']) }}
							<!--<el-input id="business_name" v-model="form['open_table_users']" placeholder="请输入开桌用户"-->
							<!--v-if="user_group === '管理员' || (form['opening_table_information_id'] && $check_field('set','open_table_users')) || (!form['opening_table_information_id'] && $check_field('add','open_table_users'))" :disabled="disabledObj['open_table_users_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','open_table_users')">{{form['open_table_users']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['opening_table_information_id'] && $check_field('set','open_table_users')) || (!form['opening_table_information_id'] && $check_field('add','open_table_users'))" id="open_table_users" v-model="form['open_table_users']" :disabled="disabledObj['open_table_users_isDisabled']">
								<el-option v-for="o in list_user_open_table_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','open_table_users')" id="open_table_users" v-model="form['open_table_users']" :disabled="true">
								<el-option v-for="o in list_user_open_table_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="open_table_users" v-model="form['open_table_users']" :disabled="disabledObj['open_table_users_isDisabled']">
							<el-option v-for="o in list_user_open_table_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
																</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','user_name') || $check_field('add','user_name') || $check_field('set','user_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="用户姓名" prop="user_name">
												<el-input id="user_name" v-model="form['user_name']" placeholder="请输入用户姓名"
							  v-if="user_group === '管理员' || (form['opening_table_information_id'] && $check_field('set','user_name')) || (!form['opening_table_information_id'] && $check_field('add','user_name'))" :disabled="disabledObj['user_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','user_name')">{{form['user_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','table_opening_time') || $check_field('add','table_opening_time') || $check_field('set','table_opening_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="开桌时间" prop="table_opening_time">
								<el-date-picker :disabled="disabledObj['table_opening_time_isDisabled']" v-if="user_group === '管理员' || (form['opening_table_information_id'] && $check_field('set','table_opening_time')) || (!form['opening_table_information_id'] && $check_field('add','table_opening_time'))" id="table_opening_time"
						v-model="form['table_opening_time']" type="datetime" placeholder="选择日期时间">
					</el-date-picker>
					<div v-else-if="$check_field('get','table_opening_time')">{{form['table_opening_time']}}</div>
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
				field: "opening_table_information_id",
				url_add: "~/api/opening_table_information/add?",
				url_set: "~/api/opening_table_information/set?",
				url_get_obj: "~/api/opening_table_information/get_obj?",
				url_upload: "~/api/opening_table_information/upload?",

				query: {
					"opening_table_information_id": 0,
				},

				form: {
								"seat_number":  '', // 座位号码
										"number_of_seats":  '', // 座位人数
										"open_table_users": 0, // 开桌用户
										"user_name":  '', // 用户姓名
										"table_opening_time":  '', // 开桌时间
											"opening_table_information_id": 0, // ID
						
				},
				disabledObj:{
								"seat_number_isDisabled": false,
										"number_of_seats_isDisabled": false,
										"open_table_users_isDisabled": false,
										"user_name_isDisabled": false,
										"table_opening_time_isDisabled": false,
										},

	
		
		
					// 用户列表
				list_user_open_table_users: [],
						// 用户组
				group_user_open_table_users: "",
				
		
	
			}
		},
		methods: {


	
	
			
	
			
	
				/**
			 * 获取普通用户用户列表
			 */
			async get_list_user_open_table_users() {
                // if(this.user_group !== "管理员" && this.form["open_table_users"] === 0) {
                //     this.form["open_table_users"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=普通用户");
                if(json.result && json.result.list){
                    this.list_user_open_table_users = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					/**
			 * 获取普通用户用户组
			 */
			async get_group_user_open_table_users() {
							this.form["open_table_users"] = this.user.user_id;
							var json = await this.$get("~/api/user_group/get_obj?name=普通用户");
				if(json.result && json.result.obj){
					this.group_user_open_table_users = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_open_table_users(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_open_table_users.source_table+"/get_obj?"
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
												_this.form["open_table_users"] = id
									_this.disabledObj['open_table_users' + '_isDisabled'] = true
						for (var i=0;i<arr.length;i++){
						  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
							for (var j = 0; j < arrForm.length; j++) {
							  if (arr[i] === arrForm[j]) {
								if (arr[i] !== "open_table_users") {
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
					get_user_open_table_users(id){
				var obj = this.list_user_open_table_users.getObj({"user_id":id});
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
									        if (this.form["table_opening_time"].indexOf("-")===-1){
            this.form["table_opening_time"] = this.$toTime(parseInt(this.form["table_opening_time"]),"yyyy-MM-dd hh:mm:ss")
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


													        if(this.form["table_opening_time"]=="0000-00-00 00:00:00"){
          this.form["table_opening_time"] = null;
        }
				if(parseInt(this.form["table_opening_time"]) > 9999){
					this.form["table_opening_time"] = this.$toTime(parseInt(this.form["table_opening_time"]),"yyyy-MM-dd hh:mm:ss")
				}
			


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
					bl = this.$check_action('/opening_table_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/opening_table_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/opening_table_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/opening_table_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/opening_table_information/view','get');
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
									this.get_list_user_open_table_users();
					this.get_group_user_open_table_users();
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
