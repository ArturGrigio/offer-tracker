<template>
	<span>
		<div class="row red darken-4 center" style="padding: 20px 0px">
			<img id="hacker-connect" src="https://inmannews-wpengine.netdna-ssl.com/wp-content/uploads/2017/03/logo_light11.png" height="80px">
			<h2 class="white-text no-margin">OFFER TRACKER</h2>
			<i id="logout" v-on:click="logout()" class="material-icons white-text xx-large pointer" style="position: absolute;right: 30px;top: 30px; display: none;">exit_to_app</i>
			<i id="back" v-on:click="back()" class="material-icons white-text xx-large pointer" style="position: absolute;left: 30px;top: 30px; display: none;">arrow_back</i>
		</div>
		<component :is='currentView'>
		</component>
	</span>
</template>

<script>
	import login from './components/Login.vue';
	import agent from './components/Agent.vue';
	import offer from './components/Offer.vue';
	import loading from './components/Loading.vue';
	import VueRouter from 'vue-router';

	window.router = new VueRouter({
		mode: 'history',
		routes: []
	});



	export default {
		router: window.router,

		data: () => {
			return {
				currentView: loading,
			}
		},

		computed: {
			shown() {
				return this.$store.state.shown
			}
		},

		watch: {
			shown(shown) {
				if(this.shown === 'agent') {
					this.currentView = agent;
					$('#hacker-connect').hide('fast')
					$('#logout').show('fast')
					$('#back').hide('fast')
				} else if(this.shown === 'offer') {
					this.currentView = offer
					$('#logout').show('fast')
					$('#back').show('fast')
				} else if(this.shown === 'login') {
					this.currentView = login
					$('#hacker-connect').show('fast')
					$('#logout').hide('fast')
					$('#back').hide('fast')
				} else {
					this.currentView = loading
				}
			}
		},

		methods: {
			logout() {
				this.$store.dispatch('logout')
			},

			back() {
				this.$store.dispatch('agent')
			}
		},

		mounted() {
			setTimeout(() => {
				this.$store.dispatch('login')
			}, 1500)
		},

		created() {
			window.route = this.$route;
		}

	}
</script>