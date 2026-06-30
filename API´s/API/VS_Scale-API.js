const VortexScale = {
    ranges: [
        { cat: 1, min: 65, max: 80 },
        { cat: 2, min: 81, max: 110 },
        { cat: 3, min: 111, max: 140 },
        { cat: 4, min: 141, max: 170 },
        { cat: 5, min: 171, max: 200 },
        { cat: 6, min: 201, max: 230 },
        { cat: 7, min: 231, max: 260 },
        { cat: 8, min: 261, max: 290 },
        { cat: 9, min: 291, max: 320 },
        { cat: 10, min: 321, max: 350 },
        { cat: 11, min: 351, max: 380 },
        { cat: 12, min: 381, max: 410 },
        { cat: 13, min: 411, max: 440 },
        { cat: 14, min: 441, max: 470 },
        { cat: 15, min: 471, max: 500 }
        // Categories 1–15 are explicitly defined above.
        // The Vortex Scale is custom and open-ended: higher categories follow a +30 mph pattern.
        // If you want to extend the predefined list, simply append more { cat, min, max } objects.
    ],

    // -------------------------
    // SAFETY CHECKS FOR WIND
    // -------------------------
    getCategory(wind) {
        if (wind === null || wind === undefined) return null;
        if (typeof wind !== "number") return null;
        if (Number.isNaN(wind)) return null;
        if (wind < 0) return null;

        for (const r of this.ranges) {
            if (wind >= r.min && wind <= r.max) return r.cat;
        }
        return Math.floor((wind - 501) / 30) + 16; // VS‑16+
    },

    // -------------------------
    // SAFETY CHECKS FOR CATEGORY
    // -------------------------
    getRange(cat) {
        if (cat === null || cat === undefined) return null;
        if (typeof cat !== "number") return null;
        if (!Number.isInteger(cat)) return null;
        if (cat < 1) return null;

        if (cat <= this.ranges.length) return this.ranges[cat - 1];

        const min = 501 + (cat - 16) * 30;
        const max = min + 29;
        return { cat, min, max };
    },

    // -------------------------
    // SAFE DESCRIPTION
    // -------------------------
    describe(cat) {
        if (cat === null || cat === undefined) return "Invalid category.";
        if (typeof cat !== "number") return "Invalid category.";
        if (!Number.isInteger(cat)) return "Invalid category.";
        if (cat < 1) return "Invalid category.";

        const r = this.getRange(cat);
        if (!r) return "Invalid category.";

        return `VS-${r.cat}: ${r.min}-${r.max} mph`;
    }
};
