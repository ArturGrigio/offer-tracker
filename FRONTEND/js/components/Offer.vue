<template>
	<span>
		<div class="row center">
			<vue-circle
					id="circle"
					:progress="percentage"
					:size="200"
					:reverse="false"
					ref="percentageCircle"
					line-cap="round"
					empty-fill="rgba(0, 0, 0, .1)"
					:animation-start-value="0.0"
					:start-angle="-1.58"
					insert-mode="append"
					:thickness="10"
					:inner-text="'<h1>'+percentage+'%</h1>'"
					:show-percent="true"
					:fill="{ color: 'rgb(255, 235, 59)' }">
	        </vue-circle>
			<p class="col s8 m10 center offset-s2 offset-m1 no-margin" v-text="'MLS NUMBER '+offer.mls_number"></p>
			<h4 class="col s8 m10 center offset-s2 offset-m1 no-margin" v-text="offer.address"></h4>

			<div class="spacer-40"></div>
			<div class="spacer-40"></div>
			<div class="spacer-10"></div>
			<div class="row center">
				<div class="col s8 m10 center offset-s2 offset-m1">
					<div v-for="event in events">
						<input v-if="event.status_code" v-on:click="statusChange(event, 0)" type="checkbox" v-bind:id="event.id" checked="checked" />
						<input v-if="!event.status_code" v-on:click="statusChange(event, 1)" type="checkbox" v-bind:id="event.id" />
						<label v-bind:for="event.id"><h5 class="black-text" style="position: relative; bottom:15px;">{{ event.description }}</h5></label>
					</div>
				</div>
			</div>
		</div>
	</span>
</template>

<script>
	import axios from 'axios';
	import VueCircle from 'vue2-circle-progress'

	export default{
		components: {
			VueCircle
		},

		computed: {
			offerLink() {
				return this.$store.state.offerLink;
			}
		},

		data: ()=> {
			return {
				offer: {},
				percentage: 10,
				events: []
			}
		},

		watch: {
			offer() {
				this.getOffer()
			}
		},

		methods:{
			getOffer() {
				let self = this;
				axios.get('/api/offers/'+self.offerLink)
					.then(response=> {
						self.offer.address = response.data.address
						self.offer.mls_number = response.data.mls_number
						axios.get('/api/events/'+response.data.id)
							.then(events=>{
								return events.data.map(e=> {
									e.status_code = (e.status_code == "1")? 1 : 0
									return e;
								})
							})
							.then(events=>{
								self.events = events
								self.calculatePercentage(events)
							})
					})
			},

			calculatePercentage(events) {
				let total = events.length;
				let completed = 0;
				events.forEach(event => {
					if(event.status_code) {
						completed += 1
					}
				})
				let percentage = parseInt((( completed / total ) * 100 ).toFixed(0))
				this.percentage = percentage
				this.$refs.percentageCircle.updateProgress(percentage)
			},

			statusChange(event, status) {
				this.events.map(e=> {
					if(e.id == event.id) {
						e.status_code = status;
					}
					return e;
				})
				this.calculatePercentage(this.events)
			}
		},

		mounted() {
			this.getOffer()
		}
	}
</script>

<style>
	#percent-text {
		top: 25% !important;
		margin-left: -85px !important;
		width: 171px !important;
	}
	[type="checkbox"]:checked+label:before{
		border-right: 2px solid #b71c1c !important;
		border-bottom: 2px solid #b71c1c !important;
	}
</style>