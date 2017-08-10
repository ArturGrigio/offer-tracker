<template>
	<span>
		<div class="row">
			<div class="col s12 m10 l8 offset-l2 offset-m1">
				 <ul class="collection with-header">
			         <li class="collection-header"><h4>Properties</h4></li>
					 <li v-on:click="openModal()" class="collection-item pointer">
				        <div>
					        <i class="material-icons yellow-text left">add_circle</i>
					        <span>Add a New Property</span>
				        </div>
			        </li>
			        <li v-for="offer in offers" v-on:click="loadOffer(offer.link)" class="collection-item pointer">
				        <div>
					        <h6 v-text="offer.mls_number" class="red-text darken-4 no-margin" style="display: inline-block"></h6>
					        <span class="grey-text lighten-2" style="padding: 0 10px">|</span>
					        <span v-text="offer.address"></span>
				        </div>
			        </li>
				</ul>
			</div>
		</div>


		<!-- Modal Structure -->
		<div id="modal1" class="modal">
			<div class="modal-content">
				<h4>ADD PROPERTY</h4>
				<div class="row">
						<div class="input-field col m7 s12">
							<input id="first_name" type="text" class="validate">
							<label for="first_name">Address</label>
						</div>
						<div class="input-field col m5 s12">
							<input id="last_name" type="text" class="validate">
							<label for="last_name">MLS Number</label>
						</div>
				</div>
			</div>
			<div class="modal-footer">
			<a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat yellow black-text">ADD</a>
			</div>
		</div>
	</span>
</template>

<script>
	import axios from 'axios';

	export default {
		data: ()=>{
			return {
				offers: []
			}
		},

		methods: {
			loadOffers() {
				let self = this;
				axios.get('/api/offers')
					.then(response => {
						self.offers = response.data
					})
			},
			loadOffer(link) {
				this.$store.dispatch('offer')
				this.$store.dispatch('offerLink', link)
			},
			openModal() {
				$('#modal1').modal('open');
			}
		},

		mounted() {
			this.loadOffers()
			$('.modal').modal({
					dismissible: true, // Modal can be dismissed by clicking outside of the modal
					opacity: .5, // Opacity of modal background
					inDuration: 300, // Transition in duration
					outDuration: 200, // Transition out duration
					startingTop: '4%', // Starting top style attribute
					endingTop: '10%', // Ending top style attribute
				}
			);
		}
	}
</script>