


const userQuizzes = document.getElementById("userQuizzes");


$.ajax({
  url: 'http://localhost:8080/Quizzery/quizzes/user',
  type: 'GET',
  dataType: 'json',
 
  contentType: 'application/json',
  success: response => {
   console.log(response);
	  
   
   
   
   
   userQuizzes.innerHTML = "";
 let counter= 0;
response.map(function(quizz) {
	counter++

    userQuizzes.innerHTML += 
    	 `
    	 
                <tr>
                  <th scope="row">${counter}</th>
                  <td>
                    <a href="editQuiz.html" class="quizName"> ${quizz.quizName} </a>
                  </td>
                  <td>
                    <button type="button" class="btn btn-secondary btn-sm">
                      <i class="glyphicon glyphicon-stats"></i> Analyze
                    </button>
                  </td>
                  <td>
                    <button type="button" class="btn btn-secondary btn-sm">
                      <i class="glyphicon glyphicon-eye-open"></i> Preview
                    </button>
                  </td>
                  <td>
                    <div class="dropdown show">
                      <button type="button" class="btn btn-success btn-sm">
                        Select action
                      </button>
                      <button
                        type="button"
                        class="btn btn-success btn-sm dropdown-toggle"
                        data-toggle="dropdown"
                        aria-expanded="false"
                      >
                        <span class="caret"></span>
                        <span class="sr-only">Toggle Dropdown</span>
                      </button>
                      <ul class="dropdown-menu" role="menu">
                        <li>
                          <a class="dropdown-item" href="#" role="button"
                            >Edit</a
                          >
                        </li>
                        <li>
                          <a class="dropdown-item" href="#" role="button"
                            >Rename</a
                          >
                        </li>
                        <li>
                          <a class="dropdown-item" href="#" role="button"
                            >Copy</a
                          >
                        </li>
                        <li>
                          <a class="dropdown-item" href="#" role="button"
                            >Merge</a
                          >
                        </li>
                        <li>
                          <a class="dropdown-item" href="#" role="button"
                            >Delete</a
                          >
                        </li>
                        <li role="separator" class="divider"></li>
                        <li>
                          <a class="dropdown-item" href="#" role="button"
                            >Move to folder</a
                          >
                        </li>
                      </ul>
                    </div>
                  </td>
                </tr> 
	  
	  
	  
	`;
	  
	  
	  
});
	  
	  
  }
});




//const resultsDiv = document.getElementById("results");
//
//
//
//
//
//
//
//const resultsFill = () => {
//        resultsDiv.innerHTML = "";
//        let matches = searchHotels();
//        matches.map(function(hotelObj) {
//            resultsDiv.innerHTML += `
//        </br>
//  
//  
//  <div
//          class="container-fluid shadow p-3"
//          style="background-color: white; border-color: black; padding: 20%;"
//        >
//          <div class="row">
//            <div
//  
//              class="col-3"
//              style=" 
//                background-image: url(${hotelObj.thumbnail});
//              "
//            ></div>
//            <div class="col-4">
//              <h1>${hotelObj.hotelName}</h1>
//              <span style="color: #f6ab3f;"
//                >${starsFill(hotelObj.rating)}</span
//              >
//              <p class="text-muted">${hotelObj.city}</p>
//              <div class="row">
//                &nbsp;&nbsp;&nbsp;&nbsp;
//                <p id="rating">${hotelObj.ratings.no}</p>
//                <p style="font-weight: bold;">&nbsp;${hotelObj.ratings.text}</p>
//                <p class="text-muted">&nbsp;(1736 reviews)</p>
//              </div>
//              <div class="row">
//                &nbsp;&nbsp;&nbsp;&nbsp;
//                <p class="text-muted">Excellent location (9.2/10)</p>
//              </div>
//            </div>
//            <div class="col-2">
//              <div style="text-align: center; padding: 5%;">
//                Hotel website
//                <p class="text-">$${hotelObj.price}</p>
//              </div>
//              <div style="text-align: center;">
//                Agoda
//                <p class="text">$${
//                  hotelObj.price + (9 / 100) * hotelObj.price
//                }</p>
//              </div>
//              <div style="text-align: center;">
//                Travelocity
//                <p class="text">$${
//                  hotelObj.price + (5 / 100) * hotelObj.price
//                } </p>
//              </div>
//              <div style="text-align: center; font-weight: bold;">
//                Travelocity
//                <p class="text">$${
//                  hotelObj.price + (5 / 100) * hotelObj.price
//                }</p>
//              </div>
//            </div>
//            <div class="col-3 text-center" style="text-align: center;">
//              <div style="color: #428500; padding: 20%; font-size: 25px">
//                Hotel Website
//                <p style="font-weight: bold; font-size: 110%;">$${
//                  hotelObj.price
//                }</p>
//                <p>3 nights for <span style="color: #428500;">$${
//                  2 * hotelObj.price
//                }</span></p>
//              </div>
//              <div class="btn btn-success" id="button_hotel">View Deal</div>
//            </div>
//          </div>
//        </div>
//        `;
//        });
//        console.log(matches[0].mapurl);
//        document.getElementById("map").src = matches[0].mapurl;
//    };