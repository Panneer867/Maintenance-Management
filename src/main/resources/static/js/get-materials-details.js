function getMaterialDetails(url) {

	if (url != null) {
		$.ajax({
			type: 'GET',
			url: url,
			success: function(data) {
				//var json = JSON.stringify(data);
				//var jsonobject = JSON.parse(json);

				$('#mat-materialId').val(data.materialId);
				$('#mat-itemName').val(data.itemName);
				$('#mat-itemId').val(data.itemId);
				$('#mat-aliasName').val(data.aliasName);
				$('#mat-category').val(data.category);
				$('#mat-brand').val(data.brand);
				$('#mat-hsnCode').val(data.hsnCode);
				$('#mat-unitOfMeasure').val(data.unitOfMeasure);
				$('#mat-costRate').val(data.costRate);
				$('#mat-mrpRate').val(data.mrpRate);
				$('#mat-quantity').val(data.quantity);
				$('#mat-entryDate').val(data.entryDate);
				$('#mat-description').val(data.description);
				$('#mat-supplier').val(data.supplier);
				$('#mat-suppliedOn').val(data.suppliedOn);
				$('#mat-gstType').val(data.gstType);
				$('#mat-igst').val(data.igst);
				$('#mat-sgst').val(data.sgst);
				$('#mat-cgst').val(data.cgst);
				$('#mat-subTotal').val(data.subTotal);
				$('#mat-grandTotal').val(data.grandTotal);
				$('#mat-invoice').val(data.invoice);
				$('#mat-receivedBy').val(data.receivedBy);
				$('#mat-receivedDate').val(data.receivedDate);
				$('#mat-itemImage').attr('src', data.imagePath + data.itemImage);
				$('#mat-subTotal').text(data.subTotal);
				$('#mat-grandTotal').text(data.grandTotal);


				$('#approve-mat-materialId').val(data.materialId);
				$('#approve-mat-itemName').val(data.itemName);
				$('#approve-mat-itemId').val(data.itemId);
				$('#approve-mat-aliasName').val(data.aliasName);
				$('#approve-mat-category').val(data.category);
				$('#approve-mat-brand').val(data.brand);
				$('#approve-mat-hsnCode').val(data.hsnCode);
				$('#approve-mat-unitOfMeasure').val(data.unitOfMeasure);
				$('#approve-mat-costRate').val(data.costRate);
				$('#approve-mat-mrpRate').val(data.mrpRate);
				$('#approve-mat-quantity').val(data.quantity);
				$('#approve-mat-entryDate').val(data.entryDate);
				$('#approve-mat-description').val(data.description);
				$('#approve-mat-supplier').val(data.supplier);
				$('#approve-mat-suppliedOn').val(data.suppliedOn);
				$('#approve-mat-gstType').val(data.gstType);
				$('#approve-mat-igst').val(data.igst);
				$('#approve-mat-sgst').val(data.sgst);
				$('#approve-mat-cgst').val(data.cgst);
				$('#approve-mat-subTotal').val(data.subTotal);
				$('#approve-mat-grandTotal').val(data.grandTotal);
				$('#approve-mat-invoice').val(data.invoice);
				$('#approve-mat-receivedBy').val(data.receivedBy);
				$('#approve-mat-receivedDate').val(data.receivedDate);
				$('#approve-mat-itemImage').val(data.itemImage);
				$('#approve-mat-imagePath').val(data.imagePath);
				$('#approve-mat-subTotal').text(data.subTotal);
				$('#approve-mat-grandTotal').text(data.grandTotal);

				$('#copy-approve-mat-materialId').val(data.materialId);
				$('#copy-approve-mat-itemName').val(data.itemName);
				$('#copy-approve-mat-itemId').val(data.itemId);
				$('#copy-approve-mat-aliasName').val(data.aliasName);
				$('#copy-approve-mat-category').val(data.category);
				$('#copy-approve-mat-brand').val(data.brand);
				$('#copy-approve-mat-hsnCode').val(data.hsnCode);
				$('#copy-approve-mat-unitOfMeasure').val(data.unitOfMeasure);
				$('#copy-approve-mat-costRate').val(data.costRate);
				$('#copy-approve-mat-mrpRate').val(data.mrpRate);
				$('#copy-approve-mat-quantity').val(data.quantity);
				$('#copy-approve-mat-entryDate').val(data.entryDate);
				$('#copy-approve-mat-description').val(data.description);
				$('#copy-approve-mat-supplier').val(data.supplier);
				$('#copy-approve-mat-suppliedOn').val(data.suppliedOn);
				$('#copy-approve-mat-gstType').val(data.gstType);
				$('#copy-approve-mat-igst').val(data.igst);
				$('#copy-approve-mat-sgst').val(data.sgst);
				$('#copy-approve-mat-cgst').val(data.cgst);
				$('#copy-approve-mat-subTotal').val(data.subTotal);
				$('#copy-approve-mat-grandTotal').val(data.grandTotal);
				$('#copy-approve-mat-invoice').val(data.invoice);
				$('#copy-approve-mat-receivedBy').val(data.receivedBy);
				$('#copy-approve-mat-receivedDate').val(data.receivedDate);
				$('#copy-approve-mat-itemImage').attr('src', data.imagePath + data.itemImage);
				$('#copy-approve-mat-subTotal').text(data.subTotal);
				$('#copy-approve-mat-grandTotal').text(data.grandTotal);
				
				
				$('#approved-mat-approvedMaterialId').val(data.approvedMaterialId);				
				$('#approved-mat-materialId').val(data.materialId);
				$('#approved-mat-itemName').val(data.itemName);
				$('#approved-mat-itemId').val(data.itemId);
				$('#approved-mat-aliasName').val(data.aliasName);
				$('#approved-mat-category').val(data.category);
				$('#approved-mat-brand').val(data.brand);
				$('#approved-mat-hsnCode').val(data.hsnCode);
				$('#approved-mat-unitOfMeasure').val(data.unitOfMeasure);
				$('#approved-mat-costRate').val(data.costRate);
				$('#approved-mat-mrpRate').val(data.mrpRate);
				$('#approved-mat-quantity').val(data.quantity);
				$('#approved-mat-entryDate').val(data.entryDate);
				$('#approved-mat-description').val(data.description);
				$('#approved-mat-supplier').val(data.supplier);
				$('#approved-mat-suppliedOn').val(data.suppliedOn);
				$('#approved-mat-gstType').val(data.gstType);
				$('#approved-mat-igst').val(data.igst);
				$('#approved-mat-sgst').val(data.sgst);
				$('#approved-mat-cgst').val(data.cgst);
				$('#approved-mat-subTotal').val(data.subTotal);
				$('#approved-mat-grandTotal').val(data.grandTotal);
				$('#approved-mat-invoice').val(data.invoice);
				$('#approved-mat-receivedBy').val(data.receivedBy);
				$('#approved-mat-receivedDate').val(data.receivedDate);
				$('#approved-mat-itemImage').attr('src', data.imagePath + data.itemImage);
				$('#approved-mat-subTotal').text(data.subTotal);
				$('#approved-mat-grandTotal').text(data.grandTotal);				

			}
		});
	}

}