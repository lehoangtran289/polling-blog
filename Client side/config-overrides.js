const { override, fixBabelImports, addLessLoader } = require("customize-cra");

/* the new version of react-app-rewired removed all the methods like injectBabelPlugin .
these methods are moved into a new package called 'customize-cra' , 
which depends on react-app-rewired@2.x . */

module.exports = override(
	fixBabelImports("import", {
		libraryName: "antd",
		libraryDirectory: "es",
		style: true // change importing css to less
	}),
	addLessLoader({
		javascriptEnabled: true,
		modifyVars: {
			"@layout-body-background": "#FFFFFF",
			"@layout-header-background": "#FFFFFF",
			"@layout-footer-background": "#FFFFFF"
		}
	})
);
