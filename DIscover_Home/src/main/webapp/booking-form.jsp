<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Discover Home | Booking Form</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/booking-form.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
	<div class="container">
		<h2>
			<i class="fas fa-calendar-plus"></i> Book a Tour
		</h2>
		<form id="tourBookingForm"
			action="${pageContext.request.contextPath}/tourBooking" method="post">
			<label for="tourName"><i class="fas fa-file-signature"></i>
				Tour Name:</label> <input type="text" id="tourName" name="tourName" required>

			<label for="description"><i class="fas fa-file-alt"></i>
				Description:</label>
			<textarea id="description" name="description" required></textarea>

			<label for="meetingPoint"><i class="fas fa-map-marked"></i>
				Meeting Point (Address):</label> <input type="text" id="meetingPoint"
				name="meetingPoint" required> <label for="tourPlaces"><i
				class="fas fa-location-arrow"></i> Tour Places:</label> <select
				id="tourPlaces" name="tourPlaces" multiple></select>

			<div id="selectedTourPlaces" class="selected-tour-places"></div>

			<label for="arrivalTime"><i class="far fa-clock"></i> Arrival
				Time:</label> <input type="datetime-local" id="arrivalTime"
				name="arrivalTime" required> <label for="departureTime"><i
				class="far fa-clock"></i> Departure Time:</label> <input
				type="datetime-local" id="departureTime" name="departureTime"
				required> <label for="noOfPersons"><i
				class="fas fa-users"></i> No of Persons:</label> <select id="noOfPersons"
				name="noOfPersons">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
			</select> <input type="hidden" id="status" name="status" value="Booked">

			<label for="price"><i class="fas fa-dollar-sign"></i> Price:</label>

			<input type="number" id="price" name="price" step="0.01" required>


			<input type="hidden" id="tourPlacesHidden" name="tourPlacesHidden"
				required> <input type="submit" value="Book Tour">
		</form>
	</div>

	<script>
        var tourPlacesData = [
        	"Agra Fort",
        	"Ajanta and Ellora Caves",
        	"Amritsar Golden Temple",
        	"Ayodhya",
        	"Darjeeling Tea Gardens",
        	"Goa Beaches",
        	"Gwalior Fort",
        	"Hampi Ruins",
        	"India Gate",
        	"Jaipur City Palace",
        	"Jaisalmer Desert Safari",
        	"Jim Corbett National Park",
        	"Kedarnath",
        	"Kerala Backwaters",
        	"Khajuraho Temples",
        	"Kolkata Victoria Memorial",
        	"Leh-Ladakh",
        	"Mahabalipuram Temples",
        	"Mahabaleshwar Hill Station",
        	"Manali",
        	"Mount Abu Trekking",
        	"Mysore Palace",
        	"Ooty Botanical Garden",
        	"Pondicherry Beaches",
        	"Prem Mandir",
        	"Pushkar Camel Fair",
        	"Rajasthan Jodhpur Fort",
        	"Ranthambore National Park",
        	"Red Fort",
        	"Rishikesh River Rafting",
        	"Shimla Mall Road",
        	"Sikkim",
        	"Srinagar",
        	"Statue of Unity",
        	"Taj Mahal",
        	"Udaipur Lake Palace",
        	"Ujjain",
        	"Varanasi Ghats"
        ];

        function populateTourPlaces() {
            var tourPlacesDropdown = document.getElementById("tourPlaces");

            tourPlacesData.forEach(function(place) {
                var option = new Option(place, place);
                tourPlacesDropdown.add(option);
            });
        }

        window.onload = function() {
            populateTourPlaces();
        };

        document.getElementById("tourPlaces").addEventListener("change", function() {
            var selectedPlace = this.value;
            if (selectedPlace) {
                addSelectedTourPlace(selectedPlace);
                this.value = '';
            }
        });

        function addSelectedTourPlace(place) {
            var selectedPlacesDiv = document.getElementById("selectedTourPlaces");
            var selectedPlaceElem = document.createElement("span");
            selectedPlaceElem.classList.add("selected-tour-place");
            selectedPlaceElem.textContent = place;
            var removeIcon = document.createElement("span");
            removeIcon.classList.add("remove-tour-place");
            removeIcon.textContent = "x";
            removeIcon.addEventListener("click", function() {
                selectedPlacesDiv.removeChild(selectedPlaceElem);
            });
            selectedPlaceElem.appendChild(removeIcon);
            selectedPlacesDiv.appendChild(selectedPlaceElem);
        }

        document.getElementById("tourBookingForm").addEventListener("submit", function() {
            var selectedPlaces = Array.from(document.querySelectorAll(".selected-tour-place")).map(elem => elem.textContent.replace("X", "").trim());
            document.getElementById("tourPlacesHidden").value = selectedPlaces.join(", ");
        });
    </script>
</body>
</html>
