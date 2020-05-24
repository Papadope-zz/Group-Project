


const userQuizzes = document.getElementById("userQuizzes");


$('#createNewQuizz').click(function(e){
	
	e.preventDefault()
	
	  var quizzName = {
		
	    quizName: $("#newQuizName").val(),
		description:"desc",
		subject:$("#newSubject").val(),
		category:$("#newCategory").val(),
		difficulty:$("#newDifficulty").val(),
		type:"type"
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
<th scope="row"> ${quizz.category}</th>
<td> ${quizz.date} </td>
<td>
   ${quizz.quizName}
</td>
<td>
   ${quizz.subject}
</td>
<td>
   ${quizz.difficulty}
</td>
<td>
    <div class="dropdown show">
        <button type="button" class="btn btn-success btn-sm">Select
            action</button>
        <button type="button" class="btn btn-success btn-sm dropdown-toggle"
            data-toggle="dropdown" aria-expanded="false" style="float: right;">
            <span class="caret"></span>
            <span class="sr-only">Toggle Dropdown</span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a class="dropdown-item" href="#" role="button">Preview</a>
            </li>
            <li>
                <a class="dropdown-item" href="/Quizzery/editQuiz?quizId=${quizz.quizId}" role="button">Edit</a>
            </li>
            <li>
                <a class="dropdown-item" href="#" role="button">Analyze</a>
            </li>
            <li role="separator" class="divider"></li>
            <li>
                <a class="dropdown-item" href="#" role="button">Delete</a>
            </li>
        </ul>
    </div>

</td>
</tr>

                 `;
	 
         });  
      }
 });





