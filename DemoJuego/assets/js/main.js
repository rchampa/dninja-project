(function(){
    var sequence = new Array();     
//  $(".col").on("touchstart", function(e){
//    
//    var x = e.pageX;
//    var y = e.pageY;
//    var clickY = y - $(this).offset().top;
//    var clickX = x - $(this).offset().left;
//    var box = this;
//    
//    var setX = parseInt(clickX);
//    var setY = parseInt(clickY);
//    $(this).find("svg").remove();
//    $(this).append('<svg><circle cx="'+setX+'" cy="'+setY+'" r="'+0+'"></circle></svg>');
//    
//
//      var c = $(box).find("circle");
//      c.animate(
//        {
//          "r" : $(box).outerWidth()/2
//        },
//        {
//          easing: "easeOutQuad",
//          duration: 400,
//          step : function(val){
//            c.attr("r", val);
//          }
//        }
//      );
//
//
//      sequence.push( $(this).data('beep'));
//      //console.info(sequence);
//
//      $(".total-beeps").text(sequence.length);
//
//  });



  $("#color-green").on("touchstart", function(){
     AndroidFunction.onClickButton(1);
     console.log('green');
  })
  $("#color-red").on("touchstart", function(){
     AndroidFunction.onClickButton(2);
     console.log('red');
  })
  $("#color-yellow").on("touchstart", function(){
     AndroidFunction.onClickButton(3);
     console.log('yellow');
  })
  $("#color-blue").on("touchstart", function(){
     AndroidFunction.onClickButton(4);
     console.log('blue');
  })       



}());