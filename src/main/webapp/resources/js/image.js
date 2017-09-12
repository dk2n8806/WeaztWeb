
$(document).ready(function() {
	'use trict';
	var $_img = $("img");
	//alert(imagePath()[0]);
	$_img.each(function() {
		var src = randomSrc();
		$(this).attr("src", src).attr("title", src);

	});
	$(".title").each(function(){
		$(this).text(randomTitle());
	});

	function randomTitle() {
		var i = Math.floor(Math.random() * titleBag().length);
		return titleBag()[i];
	}

	function randomSrc() {
		var i = Math.floor(Math.random() * imagePath().length);
	 	return imagePath()[i];
	};

	function imagePath() {
		var path = [
			"http://gtp-alkautsar.com/no_image/no-image.jpg"
			, "http://collectionsonline.nmsi.ac.uk/mobiusicons/no_image.jpg"
			, "http://gloucesterstage.com/_website/wp-content/uploads/2015/06/apples2.jpg"
			, "https://upload.wikimedia.org/wikipedia/commons/2/27/Rubens_apples_on_plate.jpg"
			, "http://www.joesmithfarms.com/images/vegetables.jpg"
			, "http://tiaschroederfitness.com/wp-content/uploads/2014/06/lets-talk-about-veg-baby.jpg"
			, "http://www.mcdonnellnursery.com/assets/images/Vegetables1.jpg"
			, "http://www.truebotanica.com/wp-content/uploads/2011/11/creams_lipbalmv1.jpg"
			, "http://cosmeticideas.com/wp-content/uploads/2015/05/Maybelline-Cosmetic-7.jpg"
			, "http://www.ust.is/library/Myndir/Atvinnulif/Efni/Snyrtivorur/iStock_000014365061Large.jpg"
			, "http://girlyenthusiast.com/wp-content/uploads/2013/03/A231604-IT-Cosmetics-Hello-Beautiful-AntiAging-Color-Collection-March-....png"
			, "http://www.cosmeticsdesign.com/var/plain_site/storage/images/publications/cosmetics/cosmeticsdesign.com/regulation-safety/mexico-publishes-labeling-standard-for-pre-packaged-cosmetics/7353198-1-eng-GB/Mexico-publishes-labeling-standard-for-pre-packaged-cosmetics.jpg"
			, "http://cdn2.bigcommerce.com/server1400/22e7d/product_images/uploaded_images/buy-cosmetic-online.jpg"
			, "http://www.flownaturalhealthcare.com/wp-content/uploads/2015/06/fotosearch_k4671947.jpg"
			, "http://santeglobal.com/images/l-arginine-cat.png"
			, "http://ww1.prweb.com/prfiles/2011/08/12/8715175/FloraBright%20from%20Nutri-Health%20Supplements.jpg"
			, "http://www.nanohealthtechnology.com/wp-content/uploads/2010/02/NanoHealthTechnology.com-Agel-Gel-Packs-1024x360.png"
			, "http://thehkshopper.com/wp-content/uploads/2013/08/vitaminshk-677x449.png"
			, "http://southfloridareporter.com/wp-content/uploads/2015/10/dietary_supplements.jpg"
			, "http://www.womenshealthmag.com/sites/womenshealthmag.com/files/images/0907-health-supplements.jpg"
			, "http://i00.i.aliimg.com/photo/v0/143991734/Gym_Supplements_For_Sale.jpg"
			, "http://store.bbcomcdn.com/images/store/prodimage/prod_24157/image_24157_original_X_450_white.jpg"
			, "http://gymowl.com/wp-content/uploads/2013/03/syntha-6-protein-300x336.jpg"
			, "http://uk.usn-sport.com/media/catalog/category/hardcoreseries.png"
			, "https://musclemaniaclub.files.wordpress.com/2010/07/nitro-tech-nop-47.jpg"


			];

		return path;
	}

	function titleBag() {
		return [
			"This is example description"
			, "Proactiv+ 3-Step Kit"
			, "DJI Phantom 3 Standard(2.7K Camera)(US/CA)"
			, "Bone Ecuador Huarache Sandal"
			, "Sangria Mini Dress by For Love & Lemons"
			, "Star Wars Ensemble Wall Mural by Retro Planet"
			, "Six Laser Hair-Removal Treatments on Small, Medium, Large, or Extra-Large Area at B Medical Spa (Up to 78% Off)"
			, "Haircut, Style, and Color Packages at Studio 69 (Up to 84% Off). Three Options Available."
			, "Two or Four Pints and a Filled Growler at Latitude 33 Brewing Co. (Up to 40% Off)"
			, "5 or 10 Aerial Silks, Trapeze, and Acrobatics Classes at Aerial Revolution Entertainment (Up to 55% Off)"
			, "Entry for One or Two to the Fit Foodie 5K (Up to 58% Off)"
			, "Spa Package for One or Two at New Image Skin Care & Spa (Up to 55% Off)"
			, "60-Minute or 90-Minute Massage at Massage by Keno (56% Off)"
			, "A cartoonist and a winemaker switch jobs: it sounds like the setup for a joke. But when graphic novelist Étienne Davodeau and vintner Richard Leroy trade places, they reveal the secrets of two fascinating art forms."
		];
	}
});