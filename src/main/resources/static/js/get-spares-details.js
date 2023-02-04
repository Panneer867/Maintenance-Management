function getSpareDetails(url) {

	if (url != null) {
		$.ajax({
			type: 'GET',
			url: url,
			success: function(data) {
				//var json = JSON.stringify(data);
				//var jsonobject = JSON.parse(json);

				$('#sap-spareId').val(data.spareId);
				$('#sap-itemName').val(data.itemName);
				$('#sap-itemId').val(data.itemId);
				$('#sap-aliasName').val(data.aliasName);
				$('#sap-category').val(data.category);
				$('#sap-brand').val(data.brand);
				$('#sap-hsnCode').val(data.hsnCode);
				$('#sap-unitOfMeasure').val(data.unitOfMeasure);
				$('#sap-costRate').val(data.costRate);
				$('#sap-mrpRate').val(data.mrpRate);
				$('#sap-quantity').val(data.quantity);
				$('#sap-entryDate').val(data.entryDate);
				$('#sap-description').val(data.description);
				$('#sap-supplier').val(data.supplier);
				$('#sap-suppliedOn').val(data.suppliedOn);
				$('#sap-gstType').val(data.gstType);
				$('#sap-igst').val(data.igst);
				$('#sap-sgst').val(data.sgst);
				$('#sap-cgst').val(data.cgst);
				$('#sap-subTotal').val(data.subTotal);
				$('#sap-grandTotal').val(data.grandTotal);
				$('#sap-invoice').val(data.invoice);
				$('#sap-receivedBy').val(data.receivedBy);
				$('#sap-receivedDate').val(data.receivedDate);
				$('#sap-itemImage').attr('src', data.imagePath + data.itemImage);
				$('#sap-subTotal').text(data.subTotal);
				$('#sap-grandTotal').text(data.grandTotal);


				$('#approve-sap-spareId').val(data.spareId);
				$('#approve-sap-itemName').val(data.itemName);
				$('#approve-sap-itemId').val(data.itemId);
				$('#approve-sap-aliasName').val(data.aliasName);
				$('#approve-sap-category').val(data.category);
				$('#approve-sap-brand').val(data.brand);
				$('#approve-sap-hsnCode').val(data.hsnCode);
				$('#approve-sap-unitOfMeasure').val(data.unitOfMeasure);
				$('#approve-sap-costRate').val(data.costRate);
				$('#approve-sap-mrpRate').val(data.mrpRate);
				$('#approve-sap-quantity').val(data.quantity);
				$('#approve-sap-entryDate').val(data.entryDate);
				$('#approve-sap-description').val(data.description);
				$('#approve-sap-supplier').val(data.supplier);
				$('#approve-sap-suppliedOn').val(data.suppliedOn);
				$('#approve-sap-gstType').val(data.gstType);
				$('#approve-sap-igst').val(data.igst);
				$('#approve-sap-sgst').val(data.sgst);
				$('#approve-sap-cgst').val(data.cgst);
				$('#approve-sap-subTotal').val(data.subTotal);
				$('#approve-sap-grandTotal').val(data.grandTotal);
				$('#approve-sap-invoice').val(data.invoice);
				$('#approve-sap-receivedBy').val(data.receivedBy);
				$('#approve-sap-receivedDate').val(data.receivedDate);
				$('#approve-sap-itemImage').val(data.itemImage);
				$('#approve-sap-imagePath').val(data.imagePath);
				$('#approve-sap-subTotal').text(data.subTotal);
				$('#approve-sap-grandTotal').text(data.grandTotal);

				$('#copy-approve-sap-spareId').val(data.spareId);
				$('#copy-approve-sap-itemName').val(data.itemName);
				$('#copy-approve-sap-itemId').val(data.itemId);
				$('#copy-approve-sap-aliasName').val(data.aliasName);
				$('#copy-approve-sap-category').val(data.category);
				$('#copy-approve-sap-brand').val(data.brand);
				$('#copy-approve-sap-hsnCode').val(data.hsnCode);
				$('#copy-approve-sap-unitOfMeasure').val(data.unitOfMeasure);
				$('#copy-approve-sap-costRate').val(data.costRate);
				$('#copy-approve-sap-mrpRate').val(data.mrpRate);
				$('#copy-approve-sap-quantity').val(data.quantity);
				$('#copy-approve-sap-entryDate').val(data.entryDate);
				$('#copy-approve-sap-description').val(data.description);
				$('#copy-approve-sap-supplier').val(data.supplier);
				$('#copy-approve-sap-suppliedOn').val(data.suppliedOn);
				$('#copy-approve-sap-gstType').val(data.gstType);
				$('#copy-approve-sap-igst').val(data.igst);
				$('#copy-approve-sap-sgst').val(data.sgst);
				$('#copy-approve-sap-cgst').val(data.cgst);
				$('#copy-approve-sap-subTotal').val(data.subTotal);
				$('#copy-approve-sap-grandTotal').val(data.grandTotal);
				$('#copy-approve-sap-invoice').val(data.invoice);
				$('#copy-approve-sap-receivedBy').val(data.receivedBy);
				$('#copy-approve-sap-receivedDate').val(data.receivedDate);
				$('#copy-approve-sap-itemImage').attr('src', data.imagePath + data.itemImage);
				$('#copy-approve-sap-subTotal').text(data.subTotal);
				$('#copy-approve-sap-grandTotal').text(data.grandTotal);
				
				
				$('#approved-sap-approvedMaterialId').val(data.approvedMaterialId);				
				$('#approved-sap-spareId').val(data.spareId);
				$('#approved-sap-itemName').val(data.itemName);
				$('#approved-sap-itemId').val(data.itemId);
				$('#approved-sap-aliasName').val(data.aliasName);
				$('#approved-sap-category').val(data.category);
				$('#approved-sap-brand').val(data.brand);
				$('#approved-sap-hsnCode').val(data.hsnCode);
				$('#approved-sap-unitOfMeasure').val(data.unitOfMeasure);
				$('#approved-sap-costRate').val(data.costRate);
				$('#approved-sap-mrpRate').val(data.mrpRate);
				$('#approved-sap-quantity').val(data.quantity);
				$('#approved-sap-entryDate').val(data.entryDate);
				$('#approved-sap-description').val(data.description);
				$('#approved-sap-supplier').val(data.supplier);
				$('#approved-sap-suppliedOn').val(data.suppliedOn);
				$('#approved-sap-gstType').val(data.gstType);
				$('#approved-sap-igst').val(data.igst);
				$('#approved-sap-sgst').val(data.sgst);
				$('#approved-sap-cgst').val(data.cgst);
				$('#approved-sap-subTotal').val(data.subTotal);
				$('#approved-sap-grandTotal').val(data.grandTotal);
				$('#approved-sap-invoice').val(data.invoice);
				$('#approved-sap-receivedBy').val(data.receivedBy);
				$('#approved-sap-receivedDate').val(data.receivedDate);
				$('#approved-sap-itemImage').attr('src', data.imagePath + data.itemImage);
				$('#approved-sap-subTotal').text(data.subTotal);
				$('#approved-sap-grandTotal').text(data.grandTotal);				

			}
		});
	}

}