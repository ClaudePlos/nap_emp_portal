import { PolymerElement } from '@polymer/polymer/polymer-element.js';
import { html } from '@polymer/polymer/lib/utils/html-tag.js';

class PayslipsView extends PolymerElement {

    static get template() {
        return html`


<style include="shared-styles">
:host {
  display: block;
}
</style>
<head>
<script src="scripts/payslips.js?1708" type="text/javascript"></script>
</head>
<body onload="load()">
<div>Content placeholder </div>
</body>


    `;
    }

    static get is() {
        return 'payslips-view';
    }
}

customElements.define(PayslipsView.is, PayslipsView);