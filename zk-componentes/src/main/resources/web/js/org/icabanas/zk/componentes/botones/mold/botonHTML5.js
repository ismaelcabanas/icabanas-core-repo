//error message html renderer
//this file will be loaded because
//the package of widget-class --  'org.icabanas.zk.componentes.botones'
//and the mold-uri of mold -- 'mold/botonHMTL5.js'
//specified in lang-addon.xml
/*
function (out) {

    var dataAttribute = this._dataAttribute;
    var dataValue = this._dataValue;

    // this.uuid is the default attribute that
    // will assigned by ZK framework
    
    // after this line, the tmp result is
    // <span id="xxxxx" class="z-errmsg">
    out.push('<button id="', this.uuid,
            '" ', this.domAttrs_(), ' ', this.getDataAttribute(), '="', this.getDataValue(), '">');

    // output end tag of span
    out.push('</button>');

    // finally, the result will be
    // <span id="xxxxx" class="z-errmsg">some message</span>
    // or
    // <span id="xxxxx" class="z-errmsg"></span>
}
*/
function(b){
	b.push('<button type="',this._type,'"',this.domAttrs_(), ' ', this.getDataAttribute(), '="', this.getDataValue(), '"');
	
	var a=this._tabindex;
	
	if(this._disabled){
		b.push(' disabled="disabled"')
	}
	
	if(a){
		b.push(' tabindex="',a,'"')
	}
	
	b.push(">",this.domContent_(),"</button>")
};