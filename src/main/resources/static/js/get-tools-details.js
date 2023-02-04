function getToolDetails(url) {

	if (url != null) {
		$.ajax({
			type: 'GET',
			url: url,
			success: function(data) {
				//var json = JSON.stringify(data);
				//var jsonobject = JSON.parse(json);

				$('#tool-toolId').val(data.toolId);
				$('#tool-itemName').val(data.itemName);
				$('#tool-itemId').val(data.itemId);
				$('#tool-aliasName').val(data.aliasName);
				$('#tool-category').val(data.category);
				$('#tool-brand').val(data.brand);
				$('#tool-hsnCode').val(data.hsnCode);
				$('#tool-unitOfMeasure').val(data.unitOfMeasure);
				$('#tool-costRate').val(data.costRate);
				$('#tool-mrpRate').val(data.mrpRate);
				$('#tool-quantity').val(data.quantity);
				$('#tool-entryDate').val(data.entryDate);
				$('#tool-description').val(data.description);
				$('#tool-supplier').val(data.supplier);
				$('#tool-suppliedOn').val(data.suppliedOn);
				$('#tool-gstType').val(data.gstType);
				$('#tool-igst').val(data.igst);
				$('#tool-sgst').val(data.sgst);
				$('#tool-cgst').val(data.cgst);
				$('#tool-subTotal').val(data.subTotal);
				$('#tool-grandTotal').val(data.grandTotal);
				$('#tool-invoice').val(data.invoice);
				$('#tool-receivedBy').val(data.receivedBy);
				$('#tool-receivedDate').val(data.receivedDate);
				$('#tool-itemImage').attr('src', data.imagePath + data.itemImage);
				$('#tool-subTotal').text(data.subTotal);
				$('#tool-grandTotal').text(data.grandTotal);


				$('#approve-tool-toolId').val(data.toolId);
				$('#approve-tool-itemName').val(data.itemName);
				$('#approve-tool-itemId').val(data.itemId);
				$('#approve-tool-aliasName').val(data.aliasName);
				$('#approve-tool-category').val(data.category);
				$('#approve-tool-brand').val(data.brand);
				$('#approve-tool-hsnCode').val(data.hsnCode);
				$('#approve-tool-unitOfMeasure').val(data.unitOfMeasure);
				$('#approve-tool-costRate').val(data.costRate);
				$('#approve-tool-mrpRate').val(data.mrpRate);
				$('#approve-tool-quantity').val(data.quantity);
				$('#approve-tool-entryDate').val(data.entryDate);
				$('#approve-tool-description').val(data.description);
				$('#approve-tool-supplier').val(data.supplier);
				$('#approve-tool-suppliedOn').val(data.suppliedOn);
				$('#approve-tool-gstType').val(data.gstType);
				$('#approve-tool-igst').val(data.igst);
				$('#approve-tool-sgst').val(data.sgst);
				$('#approve-tool-cgst').val(data.cgst);
				$('#approve-tool-subTotal').val(data.subTotal);
				$('#approve-tool-grandTotal').val(data.grandTotal);
				$('#approve-tool-invoice').val(data.invoice);
				$('#approve-tool-receivedBy').val(data.receivedBy);
				$('#approve-tool-receivedDate').val(data.receivedDate);
				$('#approve-tool-itemImage').val(data.itemImage);
				$('#approve-tool-imagePath').val(data.imagePath);
				$('#approve-tool-subTotal').text(data.subTotal);
				$('#approve-tool-grandTotal').text(data.grandTotal);

				$('#copy-approve-tool-toolId').val(data.toolId);
				$('#copy-approve-tool-itemName').val(data.itemName);
				$('#copy-approve-tool-itemId').val(data.itemId);
				$('#copy-approve-tool-aliasName').val(data.aliasName);
				$('#copy-approve-tool-category').val(data.category);
				$('#copy-approve-tool-brand').val(data.brand);
				$('#copy-approve-tool-hsnCode').val(data.hsnCode);
				$('#copy-approve-tool-unitOfMeasure').val(data.unitOfMeasure);
				$('#copy-approve-tool-costRate').val(data.costRate);
				$('#copy-approve-tool-mrpRate').val(data.mrpRate);
				$('#copy-approve-tool-quantity').val(data.quantity);
				$('#copy-approve-tool-entryDate').val(data.entryDate);
				$('#copy-approve-tool-description').val(data.description);
				$('#copy-approve-tool-supplier').val(data.supplier);
				$('#copy-approve-tool-suppliedOn').val(data.suppliedOn);
				$('#copy-approve-tool-gstType').val(data.gstType);
				$('#copy-approve-tool-igst').val(data.igst);
				$('#copy-approve-tool-sgst').val(data.sgst);
				$('#copy-approve-tool-cgst').val(data.cgst);
				$('#copy-approve-tool-subTotal').val(data.subTotal);
				$('#copy-approve-tool-grandTotal').val(data.grandTotal);
				$('#copy-approve-tool-invoice').val(data.invoice);
				$('#copy-approve-tool-receivedBy').val(data.receivedBy);
				$('#copy-approve-tool-receivedDate').val(data.receivedDate);
				$('#copy-approve-tool-itemImage').attr('src', data.imagePath + data.itemImage);
				$('#copy-approve-tool-subTotal').text(data.subTotal);
				$('#copy-approve-tool-grandTotal').text(data.grandTotal);
				
				
				$('#approved-tool-approvedtoolId').val(data.approvedtoolId);				
				$('#approved-tool-toolerialId').val(data.toolerialId);
				$('#approved-tool-itemName').val(data.itemName);
				$('#approved-tool-itemId').val(data.itemId);
				$('#approved-tool-aliasName').val(data.aliasName);
				$('#approved-tool-category').val(data.category);
				$('#approved-tool-brand').val(data.brand);
				$('#approved-tool-hsnCode').val(data.hsnCode);
				$('#approved-tool-unitOfMeasure').val(data.unitOfMeasure);
				$('#approved-tool-costRate').val(data.costRate);
				$('#approved-tool-mrpRate').val(data.mrpRate);
				$('#approved-tool-quantity').val(data.quantity);
				$('#approved-tool-entryDate').val(data.entryDate);
				$('#approved-tool-description').val(data.description);
				$('#approved-tool-supplier').val(data.supplier);
				$('#approved-tool-suppliedOn').val(data.suppliedOn);
				$('#approved-tool-gstType').val(data.gstType);
				$('#approved-tool-igst').val(data.igst);
				$('#approved-tool-sgst').val(data.sgst);
				$('#approved-tool-cgst').val(data.cgst);
				$('#approved-tool-subTotal').val(data.subTotal);
				$('#approved-tool-grandTotal').val(data.grandTotal);
				$('#approved-tool-invoice').val(data.invoice);
				$('#approved-tool-receivedBy').val(data.receivedBy);
				$('#approved-tool-receivedDate').val(data.receivedDate);
				$('#approved-tool-itemImage').attr('src', data.imagePath + data.itemImage);
				$('#approved-tool-subTotal').text(data.subTotal);
				$('#approved-tool-grandTotal').text(data.grandTotal);				

			}
		});
	}

}