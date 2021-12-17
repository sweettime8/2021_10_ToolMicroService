<script>
import {
    layoutComputed
} from "@/state/helpers";
import Vertical from "./vertical";

export default {
    components: {
        Vertical
    },
    data() {
        return {};
    },
    computed: {
        ...layoutComputed,
    },
    methods: {},
    mounted() {
       
    }
};
</script>

<template>
<div>
    <Vertical >
        <slot />
    </Vertical>

</div>
</template>
