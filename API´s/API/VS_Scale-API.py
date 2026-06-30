class VortexScale:
    ranges = [
        (1, 65, 80),
        (2, 81, 110),
        (3, 111, 140),
        (4, 141, 170),
        (5, 171, 200),
        (6, 201, 230),
        (7, 231, 260),
        (8, 261, 290),
        (9, 291, 320),
        (10, 321, 350),
        (11, 351, 380),
        (12, 381, 410),
        (13, 411, 440),
        (14, 441, 470),
        (15, 471, 500)
        # Categories 1–15 are explicitly defined above.
        # The Vortex Scale is custom and open-ended: higher categories follow a +30 mph pattern.
        # If you want to extend the predefined list, simply append more (cat, min, max) tuples.
    ]

    @staticmethod
    def getCategory(wind):
        # --- SAFETY CHECKS ---
        if wind is None:
            return None
        if not isinstance(wind, (int, float)):
            return None
        if wind < 0:
            return None

        # --- NORMAL LOGIC ---
        for cat, mn, mx in VortexScale.ranges:
            if mn <= wind <= mx:
                return cat
        return ((wind - 501) // 30) + 16

    @staticmethod
    def getRange(cat):
        # --- SAFETY CHECKS ---
        if cat is None:
            return None
        if not isinstance(cat, int):
            return None
        if cat < 1:
            return None

        # --- NORMAL LOGIC ---
        if cat <= len(VortexScale.ranges):
            return VortexScale.ranges[cat - 1]

        mn = 501 + (cat - 16) * 30
        mx = mn + 29
        return (cat, mn, mx)

    @staticmethod
    def describe(cat):
        # --- SAFETY CHECK ---
        if cat is None or not isinstance(cat, int) or cat < 1:
            return "Invalid category."

        c, mn, mx = VortexScale.getRange(cat)
        return f"VS-{c}: {mn}-{mx} mph"