"""
Flask service for data transformations convenient in Python.
"""
from flask import Flask
from flask import request
import pubmed_parser as pp
import tempfile

app = Flask(__name__)

@app.route('/pubmed_parser', methods=['POST'])
def parse_pubmed_xml():
    xml = request.data
    with tempfile.NamedTemporaryFile(suffix='.xml') as temp:
        temp.write(xml)
        parsed_xml = pp.parse_pubmed_paragraph(temp.name)
        parsed_metadata = pp.parse_pubmed_xml(temp.name)
        parsed_xml = [content['text'] for content in parsed_xml]

    return ({
        'metadata': parsed_metadata,
        'content': parsed_xml
    })

if __name__ == "__main__":
    app.run(debug=True, port=5000)
