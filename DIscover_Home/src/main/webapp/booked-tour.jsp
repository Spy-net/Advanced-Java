<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="model.Tour"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.time.LocalDateTime"%>
<!DOCTYPE html>
<html>
<head>
<title>Discover Home | Booked Tours</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/booked-tour.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>
	<div class="BookedTour">
		<div class="Heading">
			<h2>My Tours</h2>
			<h2>
				<a style="color: #DD5746"
					href="<%=request.getContextPath()%>/tourBooking"> Book Tour <i
					class="fas fa-angle-right"></i>
				</a>
			</h2>
		</div>
		<div class="TourList">
			<c:choose>
				<c:when test="${empty bookedTours}">
					<p class="Notify">No tours booked yet.........</p>
				</c:when>
				<c:otherwise>
					<c:forEach var="tour" items="${bookedTours}">
						<div class="TourItem">
							<h2>${tour.tourName}</h2>
							<p>${tour.description}</p>
							<div class="details">
								<p>
									<strong><i class="fas fa-dollar-sign"></i> Price:</strong>
									${tour.price}
								</p>
								<p>
									<strong><i class="fas fa-map-pin"></i> Meeting Point:</strong>
									${tour.meetingPoint}
								</p>
								<p>
									<strong><i class="fas fa-info-circle"></i> Status:</strong>
									${tour.status}
								</p>
								<p>
									<strong><i class="far fa-calendar-alt"></i> Booked Date:</strong>
									${tour.dateCreated.format(DateTimeFormatter.ofPattern("hh:mm a, dd-MM"))}
								</p>
								<p>
									<strong><i class="far fa-clock"></i> Arrival Time:</strong>
									${tour.arrivalTime.format(DateTimeFormatter.ofPattern("HH:mm a"))}
								</p>
								<p>
									<strong><i class="far fa-clock"></i> Departure Time:</strong>
									${tour.departureTime.format(DateTimeFormatter.ofPattern("HH:mm a"))}
								</p>
								<p>
									<strong><i class="fas fa-users"></i> No. of Persons:</strong>
									${tour.noOfPersons}
								</p>
							</div>
							<hr>
							<div style="margin: 20px 0px 40px;">
								<p>
									<strong>Booked Tour Places:</strong>
								</p>
								<ul style="display: flex; column-gap: 20px;">
									<c:forEach var="place" items="${tour.tourPlaces}">
										<li style="list-style: none;"><i class="fas fa-map-marker-alt"></i> ${place}</li>
									</c:forEach>
								</ul>
							</div>
							<hr>
							<div class="Options">
								<form action="Mytours" method="POST">
									<input type="hidden" name="id" value="${tour.tourId}">
									<input type="hidden" name="action" value="delete">
									<button type="submit" onclick="return confirm('Are you sure you want to delete this tour?')">Delete Tour</button>
									<a href="updating-tour.jsp?id=${tour.tourId}">Edit Tour</a>
								</form>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>
