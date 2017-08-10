import mainapp from './Main.vue';
import store from './store';

window.mainApp = new Vue({
	el: '#mainApp',
	store,
	components: { mainapp },
});
