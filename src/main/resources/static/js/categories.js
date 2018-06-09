function getContextPath() {
	var ctx = window.location.pathname, path = '/' !== ctx ? ctx.substring(0,
			ctx.indexOf('/', 1) + 1) : ctx;
	return path + (/\/$/.test(path) ? '' : '/');
}

function closeModal() {
	$('#response-delete').hide();
}

//for buttons 
$(function() {
	$("#requiredCatAr").hide();
	$("#onlyArabicCat").hide();
	$("#requiredCatEn").hide();
	$("#EnglishLettersOnly").hide();
	$("#requiredImageField").hide();
	$("#requiredCatDescEn").hide();
	$("#onlyEngCatDesc").hide();
	$("#requiredCatDescAr").hide();
	$("#onlyArCatDesc").hide();
	$("#saveCategory").hide();
});
function addCategory(e) {
	e.preventDefault();
	var RegExpressionAr = /^[\u0600-\u06ff ]+$/;
	var RegulerExpression = /^[^-\s][a-zA-Z\-\&_\/\s-]+$/;

	if ($("#categoryNameEng").val().trim().length == 0) {
		$("#requiredCatEn").show();
		$("#requiredCatAr").hide();
		$("##requiredCatDescEn").hide();
		$("#requiredCatDescAr").hide();
		e.preventDefault();
		return false;
	}
	;
	// ///1sttttttttt
	// if($("#categoryNameAr").val().trim().length == 0) {
	// $("#requiredCatAr").show();
	// $("##requiredCatDescEn").hide();
	// $("#requiredCatDescAr").hide();
	// $("#requiredCatEn").hide();
	// e.preventDefault();
	// return false;
	// }
	// ;
	//
	// if ($("#categoryDescriptionEn").val().trim().length == 0) {
	// $("#requiredCatEn").hide();
	// $("#requiredCatAr").hide();
	// $("##requiredCatDescEn").show();
	// $("#requiredCatDescAr").hide();
	// e.preventDefault();
	// return false;
	// }
	// ;
	// if($("#categoryDescriptionAr").val().trim().length == 0) {
	// $("#requiredCatEn").hide();
	// $("#requiredCatAr").hide();
	// $("##requiredCatDescEn").hide();
	// $("#requiredCatDescAr").show();
	// e.preventDefault();
	// return false;
	// }
	// ;

	var imgVal = $('#file').val();
	if (imgVal == '') {
		$("#requiredImageField").show();
		e.preventDefault();
		return false;
	}
	;
	// var csrfParameter = $("#_csrfParam").attr("content");
	// var csrfToken = $("#_csrf").attr("content");
	// var data = {};
	// data[csrfParameter] = csrfToken;
	// var $form = $('#categoryModel');

	var $form = $('#categoryModel')[0];

	var data = new FormData($form);
	$.ajax({
		type : "POST",
		data : data,
		enctype : 'multipart/form-data',
		// url : getContextPath() + "/category",
		url : "http://localhost:8080/categories",
		// url : "http://204.48.24.4:8080/wafaa-0.0.1-SNAPSHOT/categories",
		processData : false,
		contentType : false,
		cache : false,
		timeout : 1000000,
		success : function(response) {
			getCategories()
			// $('#fileUploadForm')[0].reset();

			// $("#categoriesOfContactGrid").html(response);
			// // $("#reqCatEn").hide();
			// // $("#EnglishLettersCat").hide();
			// // $("#contactCatLengthAr").hide();
			// // $("#acctCatLengthEn").hide();
			// // $("#reqCatEn").hide();
			// // $("#requiredCatAr").hide();
			// // getCatOfContact();
			// alert("successfully working")
			//
		},
		error : function(e) {
			console.log("the error object" + e);
			alert("Submit failed" + JSON.stringify(e));

			debugger;
			/*
			 * $("#requiredIndusAr").hide(); $("#requiredIndus").hide();
			 */
		}
	});
	$("#categoryNameEn").val("");
	$("#categoryNameAr").val("");
	$("#categoryDescriptionAr").val("");
	$("#categoryDescriptionEn").val("");
	$("#categoryid").val(0);
	$('#file').val('');
	return false;
}

$(function() {
	getCategories();
});
function getCategories() {
	$
			.ajax({
				url : "http://localhost:8080/category",
				dataType : "json",
				method : "GET",
				success : function(data) {
				
					$("#tableCat")
							.dataTable(
									{
										'data' : data,
										'bFilter' : true,
										'bInfo' : true,
										'destroy' : true,
										'columns' : [
												{
													'data' : 'categoryNameEng'
												},
												{
													'data' : 'categoryNameAr'
												},
												{
													'data' : 'categoryDescriptionEn'
												},
												{
													'data' : 'categoryDescriptionAr'
												},
												{
													'data' : data,
													'render' : function(data,
															type, row) {
														console.log(row);
														return '<a onClick= "editCategory(\''
																+ row.categoryid
																+ '\',\''
																+ row.categoryNameEng
																+ '\',\''
																+ row.categoryNameAr
																+ '\',\''
																+ row.categoryDescriptionEn
																+ '\',\''
																+ row.categoryDescriptionAr
																+ '\')'
																+ ' "><i class="fa fa-edit fa-lg"></i></a><a class="decline" title="Delete" onClick="deleteCategories('
																+ row.categoryid
																+ ')"> <i class="fa fa-remove fa-lg"></i> </a>';

													}
												} ]

									});
				}
			});
}

function editCategory(id , categoryNameEn , categoryNameAr ,categoryDescriptionEn ,categoryDescriptionAr) {
	console.log("CategoryDescription Arabic : " +  categoryDescriptionAr )
	if (categoryNameEn === "null") {
		$("#categoryNameEng").val("");
	} else {
		$("#categoryNameEng").val(categoryNameEn);
	}
	if (categoryNameAr === "null") {
		$("#categoryNameAr").val("");
	} else {
		$("#categoryNameAr").val(categoryNameAr);
	}
	
	if(categoryDescriptionEn === "null")
		{
		$("#categoryDescriptionEn").val("");
	}else {
		$("#categoryDescriptionEn").val(categoryDescriptionEn);
	}
	
	if(categoryDescriptionAr === "null")
		{
		$("#categoryDescriptionAr").val("");
		}else {
		$("#categoryDescriptionAr").val("looking for this fields");
		}
	
	
	$('#categoryAdd').hide();
	$("#saveCategory").show();
	$("#categoryid").val(id);

}

function deleteCategories(id) {
	
	$('#delete').modal('show');
	$("#Dok").click(function() {
		$.ajax({
			type : "GET",
			data : {
				'categoryId' : id,
			},
			url : "http://localhost:8080/deleteCategories",
			success : function(response) {
				getCategories()
			},
			error : function(e) {
				alert("Submit failed" + JSON.stringify(e));
			}
		});
		$('#delete').modal('hide');
	});

}


