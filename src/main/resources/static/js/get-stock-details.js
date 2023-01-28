function getMaterialDetails(data) {

	if (data != null) {
		$.ajax({
			type: 'GET',
			url: '/approvals/get/stocks/material/' + data,
			success: function(data) {
				//var json = JSON.stringify(data);
				//var jsonobject = JSON.parse(json);


				var gst = parseFloat(data.igst) || 0;
				var cost = parseFloat(data.costRate) || 0;
				var quantity = parseFloat(data.totalQuantity) || 0;
				var subTotal = cost * quantity;
				var gstVal = ((subTotal / 100) * parseFloat(gst));
				var grandTotal = parseFloat(subTotal) + parseFloat(gstVal);

				$('#mat-itemName').val(data.itemName);
				$('#mat-itemId').val(data.itemId);
				$('#mat-bundleId').val(data.bundleId);
				$('#mat-aliasName').val(data.aliasName);
				$('#mat-categoryName').val(data.categoryName);
				$('#mat-brand').val(data.brand);
				$('#mat-hsnCode').val(data.hsnCode);
				$('#mat-unitOfMeasure').val(data.unitOfMeasure);
				$('#mat-costRate').val(data.costRate);
				$('#mat-mrp').val(data.mrp);
				$('#mat-totalQuantity').val(data.totalQuantity);
				$('#mat-entryDate').val(data.entryDate);
				$('#mat-description').val(data.description);
				$('#mat-supplierName').val(data.supplierName);
				$('#mat-suppliedOn').val(data.suppliedOn);
				$('#mat-gstType').val(data.gstType);
				$('#mat-invoiceNo').val(data.invoiceNo);
				$('#mat-receivedBy').val(data.receivedBy);
				$('#mat-receivedDate').val(data.receivedDate);
				$('#material-image').attr('src', data.imagePath + data.materialImage);
				$('#mat-imagePath').val(data.imagePath);
				$('#mat-materialImage').val(data.materialImage);				
				$('#mat-igst').val(data.igst);
				$('#mat-sgst').val(data.sgst);
				$('#mat-cgst').val(data.cgst);
				$('#mat-subTotal').val(subTotal);
				$('#mat-grandTotal').val(grandTotal);
				
				$('#copy-mat-itemName').val(data.itemName);
				$('#copy-mat-itemId').val(data.itemId);
				$('#copy-mat-bundleId').val(data.bundleId);
				$('#copy-mat-aliasName').val(data.aliasName);
				$('#copy-mat-categoryName').val(data.categoryName);
				$('#copy-mat-brand').val(data.brand);
				$('#copy-mat-hsnCode').val(data.hsnCode);
				$('#copy-mat-unitOfMeasure').val(data.unitOfMeasure);
				$('#copy-mat-costRate').val(data.costRate);
				$('#copy-mat-mrp').val(data.mrp);
				$('#copy-mat-totalQuantity').val(data.totalQuantity);
				$('#copy-mat-entryDate').val(data.entryDate);
				$('#copy-mat-description').val(data.description);
				$('#copy-mat-supplierName').val(data.supplierName);
				$('#copy-mat-suppliedOn').val(data.suppliedOn);
				$('#copy-mat-gstType').val(data.gstType);
				$('#copy-mat-invoiceNo').val(data.invoiceNo);
				$('#copy-mat-receivedBy').val(data.receivedBy);
				$('#copy-mat-receivedDate').val(data.receivedDate);
				$('#copy-material-image').attr('src', data.imagePath + data.materialImage);
				$('#copy-mat-imagePath').val(data.imagePath);
				$('#copy-mat-materialImage').val(data.materialImage);				
				$('#copy-mat-igst').val(data.igst);
				$('#copy-mat-sgst').val(data.sgst);
				$('#copy-mat-cgst').val(data.cgst);
				$('#copy-mat-subTotal').text(subTotal);
				$('#copy-mat-grandTotal').text(grandTotal);
				
			}
		});
	}

}