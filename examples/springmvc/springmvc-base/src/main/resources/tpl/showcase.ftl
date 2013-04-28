<html>
<head>
<script src="http://localhost/libs/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>
<script src="http://localhost/libs/prototype/1.7.1.0/prototype.js"></script>
<link rel="stylesheet" type="text/css" href="http://localhost/libs/prettify/prettify.css">
<script src="http://localhost/libs/prettify/prettify.js"></script>
<script type="text/javascript">
jQuery(function($){
//message converter
  var resultContainer = $('.messageconverter .result');
  $('.messageconverter .btn').click(function(){
    var span = $(this);
    var dataType = span.attr('data-dataType');
    var url = span.attr('data-url');
    //window.open(url + '.' + dataType);
    $.ajax({
      url : url,
      type : span.attr('data-type')||'GET',
      dataType : dataType,
      success : function(result){
        resultContainer.attr('class', 'prettyprint');
        if(dataType == 'xml'){
          result = new XMLSerializer().serializeToString(result);
          resultContainer.addClass('lang-xml');
        }
        if(dataType == 'json'){
          result = Object.toJSON(result);
          resultContainer.addClass('lang-json');
        }
        resultContainer.text(result);
        prettyPrint();
      }
    });
  });
//crud
  var crudResult = $('.crud .result');
  $('.crud .btn').click(function(){
    var _for = $(this).attr('data-for');
    if(_for == 'create'){
      crudResult.load('${base}/user/add');
    }
    if(_for == 'load'){
      crudResult.load('${base}/user');
    }
  });
  $('.crud').on('click', '.load', function(){
    var el = $(this);
    var type = el.attr('data-type')||'GET';
    var data = {};
    /**if(type == 'DELETE'){
      type = 'POST';
      data['_method'] = 'DELETE';
    }; **/
    $.ajax({
      url : el.attr('data-url'),
      type : type,
      data : data,
      dataType : 'html',
      success : function(result){
        crudResult.html(result);
      }
    });
  });
  $('.crud').on('click', 'input[type="button"]', function(){
    var form = $(this).closest('form');
    $.ajax({
      url : form.attr('action'),
      data : form.serialize(),
      type : 'POST',
      success : function(result){
        crudResult.html(result);
      },
      dataType : 'html'
    });
  });
});
</script>
<style type="text/css">
body { margin-left: .5in }
h1, h2, h3, h4, .footer { margin-left: -.4in; }
a.Extension { display: inline-block; width: 5em; height:2.5em; border: 1px solid black; vertical-align: top; text-align: center } 
.btn{
	border:1px solid #ccc;
	cursor:pointer;
}
.load{cursor:pointer;}
.error{color:red;}
</style>
</head>
<body>

<div class="messageconverter container">
<h2>message converter:</h2>
<pre class="result" class=prettyprint></pre>
<span class="btn" data-dataType="json" data-url="${base}/user/1">json</span>
<span class="btn" data-dataType="json" data-url="${base}/user">json list</span>
<span class="btn" data-dataType="xml" data-url="${base}/user/1">xml</span>
<span class="btn" data-dataType="xml" data-url="${base}/user">xml list</span>
</div>
<div class="crud container">
<h2>CRUD:</h2>
<div class="result"></div>
<span class="btn" data-for="create">create</span>
<span class="btn" data-for="load">load</span>
</div>
</body>
</html>