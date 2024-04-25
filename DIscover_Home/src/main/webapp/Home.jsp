<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Discover Home</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>
	<div class="Rangooli">
		<img class="left-image" src="resources/left-pattern.svg"
			alt="Left Image"> <img class="mid-image"
			src="resources/right-pattern.svg" alt="Right Image"> <img
			class="right-image" src="resources/left-pattern.svg" alt="Left Image">
	</div>
	<div class="WelcomeArea">
        <div class="WelcomeText">
            <img src="resources/text-removebg-preview.png">
            <p>
                To get the best Adventure in India, you just need to leave and come where you explore
                diversity.Your Home waiting for You.</p>
            <a href="#Explore"><button>Explore Now</button></a>
        </div>
        <div class="Mandir">
            <img src="resources/RamMndir.png">
        </div>
    </div>
    <div class="video-container">
    <video autoplay muted loop controls class="Tour">
        <source src="resources/IndiaTour.mp4" type="video/mp4">
    </video>
</div>
    
    <div id="Banner">
        <img src="resources/Banner.png">
    </div>
   <jsp:include page="/gallery.jsp"></jsp:include>
    <hr>
    <div class="WonderIndia">
            <div class="About">
                <h1>About
                    <br> Incredible India
                </h1>
                <p>Interesting and Intriguing, India offers incredible holiday experiences through its cultural,
                    topography, and wildlife diversity. With these amazing and unique experiences, this south Asian
                    country conveniently finds its way into the world tourism map as one of the finest destinations for
                    a holistic vacation. India establishes its identity as the country of architectural masterpieces,
                    making it an ideal travel destination to plan a heritage tour in the world. While Taj Mahal makes
                    for the major draw on an India tour, there are a plethora of monuments and edifices in every India
                    travel guide displaying the fine architecture and grandiose of different eras in the country.
                    <br><br>
                    The diverse Indian topography adorned with the impressive Himalayas; long stretches of coastline;
                    expansive hot, cold and white salt deserts; dense forests; alpine meadows and lakes; and scenic
                    waterfalls pique the tourists’ interest. Along with the best nature sightseeing tours, India offers
                    an opportunity to visitors to have a little adventure of their own. The numerous spell-binding and
                    less-trodden trails give trekking tour opportunities in South Asia unlike any other.
                    <br><br>
                    India Tours offers a chance to explore its biodiversity in the country's many national parks and
                    wildlife reserves. An enthralling experience entails in the India wildlife tour packages that take
                    tourists to the habitats of Royal Bengal Tigers, one-horned rhinos, and snow leopards amongst many
                    rare, endangered, and unique species of flora and fauna.
                </p>
            </div>
            <img src="https://www.tourmyindia.com/imagess/varanasi-india.webp" alt="">
        </div>
</body>
</html>