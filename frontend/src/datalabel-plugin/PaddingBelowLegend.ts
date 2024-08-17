export const PaddingBelowLegends: any = {
    id: "paddingBelowLegend",
    beforeInit: function (chart: any, options: any) {
        chart.legend.afterFit = function () {
            this.height = this.height + 5;
        };
        //chart.legend.height+=5;
        console.log("ye");
    },
};
