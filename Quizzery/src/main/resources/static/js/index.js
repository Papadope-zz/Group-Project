//register request
$("#register_form").on("submit", function (event) {
  event.preventDefault();

  var formData = {
    userName: $("#register_form :input[name=userName]").val(),
    email: $("#register_form :input[name=email]").val(),
    password: $("#register_form :input[name=password]").val(),
  };

  
  
  var json = JSON.stringify(formData);

  console.log(json);
  
  $.ajax({
    type: "POST",
    url: "http://localhost:8080/Quizzery/users",
    dataType: "json",
    data: json,
    contentType: "application/json",
    success: function (data) {
      console.log(data);
    },
  });
});
