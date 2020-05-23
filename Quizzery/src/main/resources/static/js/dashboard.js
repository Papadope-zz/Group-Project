


const userQuizzes = document.getElementById("userQuizzes");


$('#createNewQuizz').click(function(e){
	
	e.preventDefault()
	
	  var quizzName = {
		
	    quizName: $("#newQuizzName").val(),
		description:"testDesc",
		subject:"testSbj",
		category:"testCat",
		difficulty:"testDiff",
		type:"testType"
	  };

	 
var json= JSON.stringify(quizzName);
	console.log(json);
	
	$.ajax({
		  url: 'http://localhost:8080/Quizzery/quizzes',
		  type: 'POST',
		  dataType: 'json',
		   data: json,
		  contentType: 'application/json',
		  success: response => {
			  console.log(response);
			  window.location.href = "/Quizzery/dashboard"  
		  }
	
	
	
	});
	
});









//creating users's Quizz Table dynamicaly
$.ajax({
  url: 'http://localhost:8080/Quizzery/quizzes',
  type: 'GET',
  dataType: 'json',
 
  contentType: 'application/json',
  success: response => {
   console.log(response);
	  
   
   
   
   
userQuizzes.innerHTML = "";
 
response.map(function(quizz) {
	

  userQuizzes.innerHTML += 
	  
             ` <tr>
                  <th scope="row">${quizz.dateCreated}</th>
                  <td>
                    <a href="editQuiz.html?quizId=${quizz.quizId}" class="quizName" > ${quizz.quizName} </a>
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
                </tr> `;
	 
         });  
      }
 });




