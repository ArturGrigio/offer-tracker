<template>
	<div>
		<div class="spacer-20"></div>
		<div class="row center">
			<div class="col s10 offset-s1 m6 offset-m3 l4 offset-l4">
				<div class="input-field col s12">
					<input id="email" type="email" class="validate">
					<label for="email"><i class="material-icons grey-text lighten-4">email</i></label>
				</div>
				<div class="input-field col s12">
					<input id="password" type="password" class="validate">
					<label for="password"><i class="material-icons grey-text lighten-4">lock</i></label>
				</div>
				<div class="col s12" style="margin-top: 30px;">
					<div id="login" v-on:click="login()" class="col s12 btn-large waves-effect waves-light yellow black-text z-depth-0">
						Submit
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		data: () => {
			return {
				email: "offer@tracker.com",
				password: "testing"
			}
		},

		methods: {
			login() {
				$('#login').addClass('disabled')
				setTimeout(()=> {
					this.$store.dispatch('agent')
				}, 1000)
			}
		}
	}
</script>