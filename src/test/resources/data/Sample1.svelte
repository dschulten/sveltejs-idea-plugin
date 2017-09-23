<div class='widget-container'>
    <Widget foo bar='static' :dynamic/>
    <button on:click="hello()">Say somepin</button>
    {{#if alive}}
    <div>Cat is alive</div>
    {{elseif alive === undefined}}
    Cat is both alive and not alive
    {{else}}
    Cat is dead
    {{/if}}
    <div class='grid'>
      {{#each rows as row, y}}
        <div class='row'>
          {{#each columns as column, x}}
            <code class='cell'>
              {{x + 1}},{{y + 1}}:
              <strong>{{row[column]}}</strong>
            </code>
          {{/each}}
        </div>
      {{/each}}
    </div>
</div>

<script>
    import Widget from './Widget.html';

    export default {
        methods: {
          hello() {
              alert("Hello!")
          }
        },
        data () {
            return {
                dynamic: 'this can change',
                alive: true
            }
        },

        components: {
            Widget
        }
    };
</script>