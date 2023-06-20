<template>
	<div class="diy_edit page_opening_table_information" id="opening_table_information_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
						<div v-if="$check_field('set','seat_number') || $check_field('add','seat_number') || $check_field('get','seat_number')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								座位号码:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_seat_number" v-model="form['seat_number']" placeholder="请输入座位号码" v-if="(form['seat_number'] && $check_field('set','seat_number')) || (!form['seat_number'] && $check_field('add','seat_number'))"  :disabled="disabledObj['seat_number_isDisabled']"/>
							<span v-else-if="$check_field('get','seat_number')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','number_of_seats') || $check_field('add','number_of_seats') || $check_field('get','number_of_seats')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								座位人数:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_number_of_seats" v-model="form['number_of_seats']" placeholder="请输入座位人数" v-if="(form['number_of_seats'] && $check_field('set','number_of_seats')) || (!form['number_of_seats'] && $check_field('add','number_of_seats'))"  :disabled="disabledObj['number_of_seats_isDisabled']"/>
							<span v-else-if="$check_field('get','number_of_seats')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','open_table_users') || $check_field('add','open_table_users') || $check_field('get','open_table_users')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								开桌用户:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_open_table_users" :disabled="disabledObj['open_table_users_isDisabled']" v-model="form['open_table_users']" v-if="(form['open_table_users'] && $check_field('set','open_table_users')) || (!form['open_table_users'] && $check_field('add','open_table_users'))" >
								<option v-for="o in list_user_open_table_users" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','open_table_users')">{{ form['open_table_users'] }}</span>
						</div>
					</div>
							<div v-if="$check_field('set','user_name') || $check_field('add','user_name') || $check_field('get','user_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								用户姓名:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_user_name" v-model="form['user_name']" placeholder="请输入用户姓名" v-if="(form['user_name'] && $check_field('set','user_name')) || (!form['user_name'] && $check_field('add','user_name'))"  :disabled="disabledObj['user_name_isDisabled']"/>
							<span v-else-if="$check_field('get','user_name')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','table_opening_time') || $check_field('add','table_opening_time') || $check_field('get','table_opening_time')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								开桌时间:
							</span>
						</div>
								<!-- 日长 -->
						<div class="diy_field diy_datetime">
							<input type="datetime-local" :disabled="disabledObj['table_opening_time_isDisabled']" id="form_table_opening_time" v-model="form['table_opening_time']" placeholder="请输入开桌时间" v-if="(form['table_opening_time'] && $check_field('set','table_opening_time')) || (!form['table_opening_time'] && $check_field('add','table_opening_time'))" />
							<span v-else-if="$check_field('get','table_opening_time')">{{ form['${obj.name}'] }}</span>
						</div>
							</div>
	




				</div>
				<div class="diy_edit_submit_box row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/opening_table_information/get_obj?",
				url_add: "~/api/opening_table_information/add?",
				url_set: "~/api/opening_table_information/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
						"seat_number": "",
							"number_of_seats": "",
							"open_table_users": 0,
							"user_name": "",
							"table_opening_time": "",
						"opening_table_information_id": 0,
				},

				obj: {
						"seat_number":  '', // 座位号码
							"number_of_seats":  '', // 座位人数
							"open_table_users": 0, // 开桌用户
							"user_name":  '', // 用户姓名
							"table_opening_time": new Date().getTime(),
						"opening_table_information_id": 0,
				},

				// 表单字段
				form: {
						"seat_number":  '', // 座位号码
							"number_of_seats":  '', // 座位人数
							"open_table_users": 0, // 开桌用户
							"user_name":  '', // 用户姓名
							"table_opening_time": new Date().getTime(),
						"opening_table_information_id": 0,
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
					
				// ID字段
				field: "opening_table_information_id",

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
					async get_user_session_open_table_users(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=普通用户");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["open_table_users"] = user_id
								_this.disabledObj['open_table_users' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
                  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
                    for (var j = 0; j < arrForm.length; j++) {
                      if (arr[i] === arrForm[j]) {
                        if (arr[i] !== "open_table_users") {
                          _this.form[arrForm[j]] = res.result.obj[arr[i]]
                          _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                          break;
                        }
                      }
                    }
                  }
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},
	
				
				
	
			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/opening_table_information/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				// if (form) {
        //   delete(form.examine_state)
        //   delete(form.examine_reply)
        //   this.obj = $.push(this.obj ,form);
				// 	this.form = $.push(this.form ,form);
				// }
				// var arr = []
				// for (let key in form) {
				// 	arr.push(key)
				// }
				// for (var i=0;i<arr.length;i++){
				// 	this.disabledObj[arr[i] + '_isDisabled'] = true
				// }
        if (form) {
          var arr = []
          for (let key in form) {
            arr.push(key)
          }
          var arrForm = []
          for (let key in this.form) {
            arrForm.push(key)
          }
          for (var i=0;i<arr.length;i++){
            if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
              for (var j = 0; j < arrForm.length; j++) {
                if (arrForm[j] === arr[i]) {
                  this.form[arrForm[j]] = form[arr[i]]
                  this.obj[arrForm[j]] = form[arr[i]]
                  this.disabledObj[arrForm[j] + '_isDisabled'] = true
                  break;
                }
              }
            }
          }
        }
									        if (JSON.stringify(this.form["table_opening_time"]).indexOf("-")===-1) {
          this.form["table_opening_time"] = this.$toTime(parseInt(this.form["table_opening_time"]), "yyyy-MM-ddThh:mm")
        }
		
        $.db.del("form");
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				// var form = $.db.get("form");
				// var obj = Object.assign({} ,form ,this.obj);
				// if (obj) {
        //   delete(obj.examine_state)
        //   delete(obj.examine_reply)
				// 	this.obj = $.push(this.obj ,obj);
				// }
				// if (form) {
        //   delete(form.examine_state)
        //   delete(form.examine_reply)
				// 	this.form = $.push(this.form ,form);
				// }

				if(func){
					func(json);
				}
			},

		},
		created() {
											this.get_user_session_open_table_users();
					this.get_list_user_open_table_users();
									},
	}
</script>

<style>




</style>
