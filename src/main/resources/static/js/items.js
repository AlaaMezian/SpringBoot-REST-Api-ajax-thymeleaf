function getContextPath() {
	var ctx = window.location.pathname, path = '/' !== ctx ? ctx.substring(0,
			ctx.indexOf('/', 1) + 1) : ctx;
	return path + (/\/$/.test(path) ? '' : '/');
}
function closeModal() {
	$('#response-delete').hide();
}

function categoryChange() {

	$.ajax({
		url : "http://localhost:8080/category",
		success : function(response) {
			/*
			 * $('#industrySelect').empty(); $('#industrySelect').append($('<option>', {
			 * value: 0, text: 'Choose Industry' }));
			 */

			$('#categorySelect').children('option:not(:first)').remove();

			for (item in response) {
				$('#categorySelect').append($('<option>', {
					value : response[item].itemId,
					text : response[item].itemTitleEn
				}));
			}

		},
		error : function(e) {
			// alert("Submit failed" + JSON.stringify(e));
		}
	});
}

$(document).ready(function() {
	$("#chooseCat").hide();
	$("#requiredItemEn").hide();
	$("#EnglishLettersOnlyItem").hide();
	$("#requiredItemAr").hide();
	$("#onlyArabicItem").hide();
	$("#requiredItemDescEn").hide();
	$("#onlyEngItemDesc").hide();
	$("#requiredItemDescAr").hide();
	$("#onlyArItemDesc").hide();
	$("#requiredImageField").hide();
	$("#requiredPrice").hide();
	
});
function addItem(e) {
	e.preventDefault();
	var RegExpressionAr = /^[\u0600-\u06ff ]+$/;
	var RegulerExpression = /^[^-\s][a-zA-Z\-\&_\/\s-]+$/;

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

	var $form = $('#ItemModel')[0];

	var data = new FormData($form);
	$.ajax({
		type : "POST",
		data : data,
		enctype : 'multipart/form-data',
		// url : getContextPath() + "/category",
		 url : "http://localhost:8080/items",
//		url : "http://204.48.24.4:8080/wafaa-0.0.1-SNAPSHOT/items",
		processData : false,
		contentType : false,
		cache : false,
		timeout : 1000000,
		success : function(response) {
//			alert("successeded")
			$("#result").html(data);
			console.log("SUCCESS : ", data);
			//			$('#fileUploadForm')[0].reset();

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
			alert("Submit failed" + JSON.stringify(e));
			/*
			 * $("#requiredIndusAr").hide(); $("#requiredIndus").hide();
			 */
		}
	});
	$("#itemTitleEn").val("");
	$("#itemTitleAr").val("");
	$("#itemDescriptionAr").val("");
	$("#itemDescriptionEn").val("");
	$("#itemId").val(0);
	$("#price").val("");
	$("#categoryId").val(0);
	$("#file").val('');
	return false;
}
$(function() {
	getItems();
});
function getItems()
{			
	$.ajax({
		url : "http://localhost:8080/item",
		dataType : "json",
		method : "GET",
		success : function(data) {
			console.log(data);
			$("#dataTableItems").dataTable({
				'data' : data,
				'bFilter' : true,
				'bInfo' : true,
				'destroy' : true,
				'columns' : [ {
					'data' : 'itemTitleEn'
				}, {
					'data' : 'itemTitleAr'
				}, {
					'data' : 'itemDescriptionAr'
				}, {
					'data' : 'itemDescriptionEn'
				}, {
					'data' : 'price'
				},
				{
					'data' : data,
					'render' : function(data,
							type, row) {
						return '<a onClick= "editItems(\''
								+ row.itemId
								+ '\',\''
								+ row.itemTitleEn
								+ '\',\''
								+ row.itemTitleAr
								+ '\',\''
								+ row.itemDescriptionAr
								+ '\',\''
								+ row.itemDescriptionEn
								+ '\',\''
								+ row.price
								+ '\')'
								+ ' "><i class="fa fa-edit fa-lg"></i></a><a class="decline" title="Delete" onClick="deleteItems('
								+ row.itemId
								+ ')"> <i class="fa fa-remove fa-lg"></i> </a>';
						
					

					}
				}
				]
			});
		}
	});
}


