// Papadope.

// back to top arrow button function
$(window).scroll(function() {
    var height = $(window).scrollTop();
    if (height > 100) {
        $('#back2Top').fadeIn();
    } else {
        $('#back2Top').fadeOut();
    }
});
$(document).ready(function() {
    $("#back2Top").click(function(event) {
        event.preventDefault();
        $("html, body").animate({ scrollTop: 0 }, "slow");
        return false;
    });

});

// toggle data function for every toggle inside the website
function toggle(source) {
    var checkboxes = document.querySelectorAll('input[type="checkbox"]');
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i] != source)
            checkboxes[i].checked = source.checked;
    }
}

// function that prevent hiding the dropdown menu in table dashboard
$(function() {
    $('.table-responsive').on('shown.bs.dropdown', function(e) {
      var t = $(this),
        m = $(e.target).find('.dropdown-menu'),
        tb = t.offset().top + t.height(),
        mb = m.offset().top + m.outerHeight(true),
        d = 20; // Space for shadow + scrollbar.   
      if (t[0].scrollWidth > t.innerWidth()) {
        if (mb + d > tb) {
          t.css('padding-bottom', ((mb + d) - tb));
        }
      } else {
        t.css('overflow', 'visible');
      }
    }).on('hidden.bs.dropdown', function() {
      $(this).css({
        'padding-bottom': '',
        'overflow': ''
      });
    });
  });

// checkbox delete user Appear / Disappear
$(document).ready(function() {
    
    var $submit = $("#submit_prog").hide(),
        $cbs = $('input[name="delete"]').click(function() {
            $submit.toggle( $cbs.is(":checked") );
        });

});



//register request
		$('#register_form').on('submit', function(event) {
			
		  event.preventDefault();
		
		  
		  
		  var formData = {

			        userName: $('#register_form :input[name=userName]').val(),
			        email: $('#register_form :input[name=email]').val(),
			        password: $('#register_form :input[name=password]').val()
			       
			    };
		  
		 var json= JSON.stringify(formData);
	
		  $.ajax({
		    type: 'POST',
		    url: 'http://localhost:8080/Quizzery/users',
		    dataType: 'json',
		    data: json,
		    contentType: 'application/json',
		    success: function(data) {
		    console.log(data)	;
		      
		    }
		  });
		});
		
		
		
//		$('#login_form').on('submit', function(event) {
//			
//			  event.preventDefault();
//			
//			  
//			  
//			  var formData = {
//
//				       
//				        email: $('#login_form :input[name=email]').val(),
//				        password: $('#login_form :input[name=password]').val()
//				       
//				    };
//			  
//			 var json= JSON.stringify(formData);
//		
//			  $.ajax({
//			    type: 'POST',
//			    url: 'http://localhost:8080/Quizzery/login',
//			    dataType: 'json',
//			    data: json,
//			    contentType: 'application/json',
//			    success: function(data) {
//			    console.log(data)	;
//			      
//			    }
//			  });
//			});
		
		
		