<#assign fragmentCollectionModel = dataFactory.newFragmentCollectionModel(groupId) />

${dataFactory.toInsertSQL(fragmentCollectionModel)}

<#assign fragmentEntryModel = dataFactory.newFragmentEntryModel(groupId, fragmentCollectionModel) />

${dataFactory.toInsertSQL(fragmentEntryModel)}

<#assign contentLayoutModels = dataFactory.newContentLayoutModels(groupId) />

<#list contentLayoutModels as contentLayoutModel>
	<@insertContentLayout
		_fragmentEntryModel=fragmentEntryModel
		_layoutModel=contentLayoutModel
	/>

	${csvFileWriter.write("fragment", contentLayoutModel.friendlyURL + "\n")}
</#list>